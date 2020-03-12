package com.crm.web.controller;

import com.crm.client.UserClient;
import com.crm.enums.ExceptionEnums;
import com.crm.exception.CrmException;
import com.crm.utils.JwtTokenUtils;
import com.crm.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String username;
    private String password;
}

@RestController
@RequestMapping("/auth")
@Api(value = "登陆认证",tags = {"登陆认证"})
public class LoginController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserClient userClient;

    @PostMapping("/login")
    @ApiOperation("登陆")
    public ResponseEntity login(@RequestBody User user) {
        // 要去查询数据库
        String username = user.getUsername();
        String password = user.getPassword();
        Object obj = userClient.login(username, password);
        if(obj != null) {
            // 1.登陆成功，那么就需要产生token
            String token = JwtTokenUtils.createToken(username);
            // 2. 把token存入redis
            redisTemplate.opsForValue().set(username,token);
            redisTemplate.expire(username,JwtTokenUtils.EXPIRATION, TimeUnit.MILLISECONDS);
            // 2.响应回去

            /**
             *   3.怎么去校验token
             *      1.判断请求携带过来的token是否与我们登陆回去的token是否一致
             *          登陆成功以后需要把token存入到redis中
             *      2.判断token是否过期
             */
            return ResponseEntity.ok(token);

        } else {
            return ResponseEntity.status(ExceptionEnums.USERNAME_OR_PASSWORD_ERROR.getCode())
                    .body(new ResponseResult(ExceptionEnums.USERNAME_OR_PASSWORD_ERROR));
        }
    }

    @GetMapping("/info")
    public ResponseEntity info(HttpServletRequest request) {
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            throw  new CrmException(ExceptionEnums.TOKEN_ERROR);
        }
        return ResponseEntity.ok(JwtTokenUtils.getUsername(token));
    }

    @GetMapping("/loginOut")
    public ResponseEntity loginOut(HttpServletRequest request) {
        // 获取token
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            throw  new CrmException(ExceptionEnums.TOKEN_ERROR);
        }
        redisTemplate.delete(JwtTokenUtils.getUsername(token));
        return ResponseEntity.ok(ExceptionEnums.SUCCESS);
    }
}

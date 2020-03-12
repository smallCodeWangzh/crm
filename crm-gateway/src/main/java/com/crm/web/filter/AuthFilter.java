package com.crm.web.filter;

import com.crm.enums.ExceptionEnums;
import com.crm.utils.JsonUtils;
import com.crm.utils.JwtTokenUtils;
import com.crm.utils.ResponseResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFilter extends ZuulFilter {

    // 注入redis操作对象，这样就可从redis中获取数据
    @Autowired
    private StringRedisTemplate redisTemplate;

    // 返回 "pre"代表是前置过滤器
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    // 设置过滤器的优先级别 数字越小，级别久越高
    @Override
    public int filterOrder() {
        return 0;
    }

    // true 代表要只从这个过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 用来写具体的业务逻辑
    @Override
    public Object run() throws ZuulException {
        // 获取requestContext
        RequestContext requestContext = RequestContext.getCurrentContext();
        try {
            // 得到request
            HttpServletRequest request = requestContext.getRequest();
            // 如果是访问登陆请求，那么就不需要校验token
            // 如果说是访问swagger 也不需要校验token
            String uri = request.getRequestURI();
            if (uri.contains("/crm-auth") || uri.contains("/v2/api-docs")) {
                // 就表示放行
                return null;
            }
            // 获取token
            String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
            if (StringUtils.isBlank(token)) {
                // 如果token为空，那么直接返回数据给客户端
                errorReponse(requestContext,ExceptionEnums.TOKEN_ERROR);
            } else {

                // 从redis中取出token，key为用户名 value:token
                // 如果是穿了缺失一半的token，那么这个方法就会抛出异常
                // 调用全局异常处理机制（CrmException）
                // Exception
                String username = JwtTokenUtils.getUsername(token);

                /**
                 *  redisToken为空  redis tokeng过期了
                 *  用户名错误
                  */
                String redisToken = redisTemplate.opsForValue().get(username);
                // token正常
                if(token.equals(redisToken) && !JwtTokenUtils.isExpiration(token)) {
                    // token合法
                    return null;
                } else {
                    // token不合法
                    errorReponse(requestContext,ExceptionEnums.TOKEN_ERROR);
                }

            }
        } catch (Exception e) {
            errorReponse(requestContext,ExceptionEnums.TOKEN_ERROR);
        }
        return null;
    }

    public void errorReponse(RequestContext requestContext,ExceptionEnums exceptionEnums) {
       // 设置响应类型
        requestContext.addZuulResponseHeader("content-type","application/json;charset=utf-8");
        // 设置响应状态码
        requestContext.setResponseStatusCode(exceptionEnums.getCode());
        // 设置响应信息 json
        requestContext.setResponseBody(JsonUtils.serialize(new ResponseResult(exceptionEnums)));
        // 阻止请求向下面的过滤器转发
        requestContext.setSendZuulResponse(false);
    }
}

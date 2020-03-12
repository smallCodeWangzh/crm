package  com.crm.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class Swagger2Config implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {

        List resources = new ArrayList<>();

        /**
         *  crm-systemset ---> 这个就是个服务的名字，虽然可以随便写，但是最好不要
         * /member/v2/api-docs ---> member就是application.yml配置的路由路径，后面的/v2/api-docs固定写法
         */
        resources.add(swaggerResource("系统设置", "/api/crm-systemset/v2/api-docs", "2.0"));
        resources.add(swaggerResource("登陆认证", "/api/crm-auth/v2/api-docs", "2.0"));
      //  resources.add(swaggerResource("crm-systemset", "/api/crm-systemset/v2/api-docs","2.0"));
        return resources;
    }

    private Object swaggerResource(String name, String location, String version) {

        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}

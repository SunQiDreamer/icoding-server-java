package com.sq.ic.common.cfg;

import com.fasterxml.jackson.core.filter.TokenFilter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

// localhost:<port>/swagger-ui/index.html
@Configuration
public class SwaggerCfg implements InitializingBean {

    @Autowired
    private Environment environment;
    private boolean enable;

    private ApiInfo apiInfo(String title,  String description)  {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket studentDocket() {
        return groupDocket(
                "01_学生",
                "/student.*",
                "学生模块文档",
                "学生系统");
    }

    @Bean
    public Docket gradeDocket() {
        return groupDocket(
                "02_班级",
                "/grade.*",
                "班级模块文档",
                "班级系统");
    }

    @Bean
    public Docket hobbyDocket() {
        return groupDocket(
                "03_爱好",
                "/hobby.*",
                "爱好模块文档",
                "爱好系统");
    }

    private Docket groupDocket(String group,
                               String regex,
                               String title,
                               String description) {
        return basicDocket()
                .groupName(group)
                .apiInfo(apiInfo(title, description))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex(regex))
                .build();
    }

    private Docket basicDocket() {
        RequestParameter token = new RequestParameterBuilder()
                .name("token")
                .description("用户登录令牌")
                .in(ParameterType.HEADER)
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .globalRequestParameters(List.of(token))
                .ignoredParameterTypes(HttpSession.class,
                        HttpServletRequest.class,
                        HttpServletResponse.class)
                .enable(enable);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        enable = environment.acceptsProfiles(Profiles.of("dev", "test"));
    }


}

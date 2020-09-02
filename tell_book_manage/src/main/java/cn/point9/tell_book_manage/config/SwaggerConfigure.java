package cn.point9.tell_book_manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by limengwei on 2019-08-16
 **/
@EnableSwagger2
@Configuration
public class SwaggerConfigure {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.point9.tell_book_manage.controler"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("tell_book_manage v1.0.0")
                .description("基于Spring Boot和MyBatis-Plus实现 tellbook 通讯录管理系统")
                .termsOfServiceUrl("https://point9.cn")
                .contact(new Contact("Point9", "https://point9.cn", "mail@point9.cn"))
                .version("1.0")
                .license("license")
                .licenseUrl("https://point9.cn/license")
                .build();
    }

}
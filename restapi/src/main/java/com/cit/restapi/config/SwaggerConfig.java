package com.cit.restapi.config;

import com.cit.restapi.application.SwaggerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by odziea on 11/12/2018.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    SwaggerController swaggerController() { return new SwaggerController(); }
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    @Bean
    public Docket createRestApi() {


        Class[] ignoreTheseModels = {};

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN ))
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Clone access card Validation REST API ")
                .description(" This validation API detects use of cloned RFID access cards and notifies about the event in real-time.")
                .version("1.0")
                .termsOfServiceUrl("Terms of service")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact("John Nolan, Anna Odziemczyk, Eamon Foy - https://github.com/TeamBravo2018/rfid-clone-spy")
                .build();
    }

}

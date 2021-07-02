package com.sapient.card.app.config;
/*
 *  Created by Girish Kumar CH on 7/1/21
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig implements WebFluxConfigurer {


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Credit Card Application")
                .description("Credit Card Application Challenge")
                .version("1.0").build();
    }

}

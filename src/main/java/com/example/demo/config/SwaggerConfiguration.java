package com.example.demo.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {	
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(applicationApiInfo())
				.select()
				.paths(PathSelectors.regex("/.*"))
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
				.build();
	}

	private ApiInfo applicationApiInfo() {
		
		return new ApiInfoBuilder().title("Creación en H2 de clientes y exportación a Excel").version("1.0").license("Apache")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")			
				.description("Generar clientes y exportarlos a Excel")				
				.build();
	}
	
}

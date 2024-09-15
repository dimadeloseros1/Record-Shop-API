package com.northcoders.RecordShopApi;
//package io.swagger.v3.oas.models

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class RecordShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordShopApiApplication.class, args);
	}

	@Bean
	public OpenAPI todoApiInfo() {
		return new OpenAPI()
				.info(new Info().title("Record Shop Api")
						.description("Need a list of artist and genres of music? This API list is for you \uD83D\uDE3A")
						.version("v1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}

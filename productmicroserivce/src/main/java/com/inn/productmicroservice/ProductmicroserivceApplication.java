package com.inn.productmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient

public class ProductmicroserivceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductmicroserivceApplication.class, args);
	}

}

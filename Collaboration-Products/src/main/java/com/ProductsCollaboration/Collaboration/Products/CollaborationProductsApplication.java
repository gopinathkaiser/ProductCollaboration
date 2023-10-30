package com.ProductsCollaboration.Collaboration.Products;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CollaborationProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollaborationProductsApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){

		return new ModelMapper();
	}
}

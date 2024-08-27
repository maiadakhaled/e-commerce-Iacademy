package com.example.e_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				ECommerceApplication.class, args
		);
	}

}


// 100 - 199  =
// 200 - 299 =  success
//300 - 399 =
//400 - 499 = failure
//500 - 599 = internal error / server error


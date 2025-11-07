package com.etiya.salesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SalesserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesserviceApplication.class, args);
	}

}

package com.thalesbensi.sales_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients()
@SpringBootApplication
public class SalesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesMsApplication.class, args);
	}

}

package edu.sjsu.cmpe275.lab2.phoenix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PhoenixApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoenixApplication.class, args);
	}

}

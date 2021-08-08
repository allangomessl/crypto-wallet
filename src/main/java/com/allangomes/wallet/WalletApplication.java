package com.allangomes.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
@EnableFeignClients
public class WalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

	@Bean
	public ForkJoinPool pool() {
		return new ForkJoinPool(3);
	}

	@Bean
	public HttpMessageConverters converters() {
		return new HttpMessageConverters();
	}
}

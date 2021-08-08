package com.allangomes.wallet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
public class ConfigTest {

    @Bean
    public ForkJoinPool pool() {
        return new ForkJoinPool(2);
    }
}

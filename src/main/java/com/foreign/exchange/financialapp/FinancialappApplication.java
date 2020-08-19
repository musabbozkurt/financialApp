package com.foreign.exchange.financialapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.foreign.exchange.financialapp.*"})
public class FinancialappApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialappApplication.class, args);
    }

}

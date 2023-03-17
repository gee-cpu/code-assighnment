package com.gonchaba.codeassighnment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "operation")
@Data
@PropertySource("classpath:application.yml")
public class OperationConfig {

    private double additionCost =2;
    private double subtractionCost=2;
    private double multiplicationCost=2;
    private double divisionCost=2;
    private double squareRootCost=2;
    private double randomStringCost=2;
    private String randomStringUrl="https://www.random.org/clients";
}
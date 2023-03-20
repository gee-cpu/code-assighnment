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

    private double additionCost;
    private double subtractionCost;
    private double multiplicationCost;
    private double divisionCost;
    private double squareRootCost;
    private double randomStringCost;
    private String randomStringUrl;
}
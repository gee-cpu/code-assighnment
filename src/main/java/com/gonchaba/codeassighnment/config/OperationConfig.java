package com.gonchaba.codeassighnment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "operation")
@Data
public class OperationConfig {

    private double additionCost;
    private double subtractionCost;
    private double multiplicationCost;
    private double divisionCost;
    private double squareRootCost;
    private double randomStringCost;
    private String randomStringUrl;
}
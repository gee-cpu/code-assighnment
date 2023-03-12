package com.gonchaba.codeassighnment.config;


import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class RandomStringConfig {

        @Bean
        public RandomStringGenerator randomStringGenerator() {
            return new RandomStringGenerator.Builder()
                    .withinRange('0', 'z')
                    .filteredBy(Character::isLetterOrDigit)
                    .build();
        }



}

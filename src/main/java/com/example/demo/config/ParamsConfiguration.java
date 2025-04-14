package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class ParamsConfiguration {

    @Value("${jwt.secret-key:}")
    private String jwtSecretKey;
}

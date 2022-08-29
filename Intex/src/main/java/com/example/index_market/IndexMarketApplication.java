package com.example.index_market;

import com.example.index_market.properties.OpenApiProperties;
import com.example.index_market.properties.ServerProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties({
        OpenApiProperties.class,
        ServerProperties.class
})
@OpenAPIDefinition
@EnableFeignClients
public class IndexMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndexMarketApplication.class, args);
    }

}

package fr.insalyon.tc.dia.springapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootHolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHolApplication.class, args);
    }

    @Bean
    public HealthIndicator authServiceHealthIndicator() {
        return () -> {
            return Health.up().build();
        };
    }
}

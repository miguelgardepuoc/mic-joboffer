package com.antharos.joboffer.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.antharos.joboffer")
@EntityScan("com.antharos.joboffer.infrastructure.out.repository")
@EnableJpaRepositories("com.antharos.joboffer.infrastructure.out.repository")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

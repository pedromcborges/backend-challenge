package com.backend.challenge.domain.repository.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.backend.challenge.domain.repository")
@EntityScan(basePackages = "com.backend.challenge.domain")
public class RepositoriesConfig {

}

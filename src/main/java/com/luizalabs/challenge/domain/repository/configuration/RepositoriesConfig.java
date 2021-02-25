package com.luizalabs.challenge.domain.repository.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.luizalabs.challenge.domain.repository")
@EntityScan(basePackages = "com.luizalabs.challenge.domain")
public class RepositoriesConfig {

}

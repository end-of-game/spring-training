package org.example.organizer.api;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("org.example.organizer.repository")
@EntityScan("org.example.organizer.model")
@Configuration
public class PersistenceConfiguration {

}

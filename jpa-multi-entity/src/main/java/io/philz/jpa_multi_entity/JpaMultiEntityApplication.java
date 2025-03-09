package io.philz.jpa_multi_entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaMultiEntityApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaMultiEntityApplication.class, args);
	}
}

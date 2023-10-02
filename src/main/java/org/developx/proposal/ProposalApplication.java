package org.developx.proposal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ProposalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProposalApplication.class, args);
	}

}

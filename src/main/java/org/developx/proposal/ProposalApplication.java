package org.developx.proposal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class ProposalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProposalApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	ApplicationRunner runner(ConditionEvaluationReport report) {
		return args -> {
			report.getConditionAndOutcomesBySource().entrySet().stream()
					.filter(co -> co.getValue().isFullMatch())
					.filter(co -> co.getKey().indexOf("Jmx") < 0)
					.forEach(co -> {
								log.debug(co.getKey());
								co.getValue().forEach(c->{
									log.debug("\t" + c.getOutcome());
								});
								log.debug("\n");
							}
					);
		};
	}

}

package taskdb.taskdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaskdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskdbApplication.class, args);
	}

}

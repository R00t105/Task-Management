package backend.taskmanagementrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaskManagementRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementRestApiApplication.class, args);
    }

}

package pro.kensait.spring.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories({"pro.kensait.spring.bookstore.repos"})
@EntityScan({"pro.kensait.spring.bookstore.entity"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
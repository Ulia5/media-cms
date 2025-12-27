package org.example.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.example.media.repository")
public class MediaCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediaCmsApplication.class, args);
    }
}

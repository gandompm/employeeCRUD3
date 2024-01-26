package com.root.springboot.demo;

import com.root.springboot.demo.conf.MessageSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(MessageSourceConfig.class)
public class ThymeleafdemoApplication {
    public ThymeleafdemoApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafdemoApplication.class, args);
    }
}

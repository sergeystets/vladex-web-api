package com.sergeystets.vladex.web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VladexWebApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(VladexWebApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(VladexWebApiApplication.class);
    }
}

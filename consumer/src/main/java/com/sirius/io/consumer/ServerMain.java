package com.sirius.io.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.multiverse.eventkit.kit.annotation.EnableKitSupports;

@EnableKitSupports
@SpringBootApplication
public class ServerMain {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServerMain.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
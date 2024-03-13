package com.sirius.io.sob9m01001;

import com.multiverse.eventkit.kit.annotation.EnableKitSupports;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableKitSupports
@SpringBootApplication
public class ServerMain {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServerMain.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
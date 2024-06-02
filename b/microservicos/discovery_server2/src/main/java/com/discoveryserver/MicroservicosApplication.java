package com.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroservicosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicosApplication.class, args);
        //System.out.println("HELLLOOOOO");
    }

}

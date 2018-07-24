package org.tis.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ToolsAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsAuthServerApplication.class, args);
    }
}

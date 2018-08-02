package org.tis.senior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tis.tools.starter.swagger.EnableSwagger2Doc;

/**
 * Hello world!
 *
 */
@EnableSwagger2Doc
@SpringBootApplication
public class ToolsSeniorServiceApplication {

    public static void main( String[] args ) {
        SpringApplication.run(ToolsSeniorServiceApplication.class, args);
        System.out.println( "Hello Senior!" );
    }
}


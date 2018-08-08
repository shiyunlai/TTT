package org.tis.tools.senior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.tis.tools.starter.swagger.EnableSwagger2Doc;

/**
 * Hello world!
 *
 */
@EnableSwagger2Doc
@SpringBootApplication
@ComponentScan("org.tis.tools")
public class ToolsSeniorServiceApplication {

    public static void main( String[] args ) {
        SpringApplication.run(ToolsSeniorServiceApplication.class, args);
        System.out.println( "Hello Senior!" );
    }
}


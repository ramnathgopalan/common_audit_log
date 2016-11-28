package com.autodesk.commonlog.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.autodesk")
@EnableAutoConfiguration
public class DemoApplication {

	public static void main(String[] args) {

    SpringApplication app =
        new SpringApplication(DemoApplication.class);
    app.run(args);
	}
}

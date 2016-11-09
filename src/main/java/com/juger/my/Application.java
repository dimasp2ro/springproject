package com.juger.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@PropertySources({
    @PropertySource("application.properties"),
    @PropertySource(value = "file:${Dlardi.conf}/application.properties", ignoreResourceNotFound = true)
})
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}

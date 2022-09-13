package io.javabrains.CourseApidata;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@EnableCaching
@ComponentScan("io.javabrains.CourseApidata.topics")
@SpringBootApplication
@OpenAPIDefinition(info =@Info(title = "Topic API",version = "1.0",description = "This API is for Topics"))
public class CourseApidataApplication {

	public static void main(String[] args) {

		SpringApplication.run(CourseApidataApplication.class, args);
	}

}
//http://localhost:8090/topicapi/swagger-ui/index.html
//http://localhost:8090/topicapi/v3/api-docs
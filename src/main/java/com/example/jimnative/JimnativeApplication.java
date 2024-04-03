package com.example.jimnative;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@EnableR2dbcRepositories
public class JimnativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JimnativeApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> getEmployeeByIdRoute(EmployeeService employeeRepository) {
		return route(GET("/employees/{id}"),
				req -> ok().body(
						employeeRepository.findEmployeeById(req.pathVariable("id")), Employee.class))
				.andRoute(PUT("/employees/{id}"),
						req -> ok().body(employeeRepository.updateEmployee(req.bodyToMono(Employee.class)), Employee.class))
				.andRoute(POST("/employees"),
						req -> ok().body(employeeRepository.updateEmployee(req.bodyToMono(Employee.class)), Employee.class));
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(
				new ClassPathResource("schema.sql"),
				new ClassPathResource("data.sql")));

		return initializer;
	}

}

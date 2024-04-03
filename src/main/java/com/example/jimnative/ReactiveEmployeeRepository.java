package com.example.jimnative;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReactiveEmployeeRepository extends ReactiveCrudRepository<Employee, String> {

    @Query("SELECT * FROM employee WHERE name = :lastname")
    Flux<Employee> findByLastName(String lastName);

}

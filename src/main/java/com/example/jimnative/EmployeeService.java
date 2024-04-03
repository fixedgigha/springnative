 package com.example.jimnative;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final ReactiveEmployeeRepository realRepository;

    public Mono<Employee> findEmployeeById(String id) {
        log.info("Looking for employee {}", id);
        return realRepository.findById(id);
    }

    public Flux<Employee> findAllEmployees() {
        return realRepository.findAll();
    }

    public Mono<Employee> updateEmployee(Mono<Employee> employee) {
        return employee.doOnNext(employee1 -> log.info("I got {}", employee1)).flatMap(realRepository::save);
    }
}

package com.example.jimnative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterReflectionForBinding(Employee.class)
@Table
public class Employee implements Serializable {

    @Id
    private Long id;
    private String name;

}

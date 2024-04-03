package com.example.jimnative;

import com.example.jimnative.Employee;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@Configuration
@ImportRuntimeHints(JacksonRuntimeHints.PropertyNamingStrategyRegistrar.class)
public class JacksonRuntimeHints {

    static class PropertyNamingStrategyRegistrar implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            try {
                hints.reflection()
                        .registerField(PropertyNamingStrategies.class.getDeclaredField("SNAKE_CASE"));
                hints.serialization().registerType(Employee.class);
            } catch (NoSuchFieldException e) {
                // ...
            }
        }
    }

}

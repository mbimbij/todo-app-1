package com.example.todoapp.cucumber;

import com.example.todoapp.TodoApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = {
        SpringCucumberContextConfiguration.SpringConfiguration.class,
        TodoApplication.class
})
@CucumberContextConfiguration
public class SpringCucumberContextConfiguration {

    @Bean
    public CucumberContext cucumberContext() {
        return new CucumberContext();
    }

    public static class SpringConfiguration {
        @Bean
        public CucumberContext cucumberContext() {
            return new CucumberContext();
        }
    }
}

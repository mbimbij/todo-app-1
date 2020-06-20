package com.example.todoapp.cucumber;

import com.example.todoapp.TodoApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = {
        SpringCucumberContextConfiguration.SpringConfiguration.class,
        TodoApplication.class
})
@CucumberContextConfiguration
public class SpringCucumberContextConfiguration {

    public static class SpringConfiguration {
        @Bean
        public CucumberContext cucumberContext() {
            return new CucumberContext();
        }
    }
}

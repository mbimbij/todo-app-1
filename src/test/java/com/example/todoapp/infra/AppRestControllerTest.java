package com.example.todoapp.infra;

import com.example.todoapp.TodoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(AppRestController.class)
@Import(TodoApplication.class)
class AppRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void name() {
        assertThat(true).isTrue();
    }
}
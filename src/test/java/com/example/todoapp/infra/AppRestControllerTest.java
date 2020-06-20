package com.example.todoapp.infra;

import com.example.todoapp.TodoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(AppRestController.class)
@Import(TodoApplication.class)
@AutoConfigureDataJpa
class AppRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value = "user2")
    @Test
    void name() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/listItems"));
        assertThat(true).isTrue();
    }
}
package com.example.todoapp.infra.itemrepository;

import com.example.todoapp.TodoApplication;
import com.example.todoapp.core.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TodoApplication.class)
class JpaItemRepositoryIT {
    @Autowired
    private ItemRepository jpaItemRepository;

    @Test
    void name() {
        System.out.println();
    }
}
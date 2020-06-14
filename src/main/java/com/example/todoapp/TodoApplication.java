package com.example.todoapp;

import com.example.todoapp.core.CreateItemUsecase;
import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.ListItemsUsecase;
import com.example.todoapp.core.UserManager;
import com.example.todoapp.core.UserRepository;
import com.example.todoapp.infra.InMemoryItemRepository;
import com.example.todoapp.infra.InMemoryUserRepository;
import com.example.todoapp.infra.UserManagerMock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public ItemRepository inMemoryItemRepository() {
        InMemoryItemRepository repository = new InMemoryItemRepository();
        repository.save(Item.create("task1", "user1", "todo"));
        repository.save(Item.create("task2", "user1", "doing"));
        repository.save(Item.create("task3", "user1", "done"));
        repository.save(Item.create("task4", "user2", "todo"));
        repository.save(Item.create("task5", "user2", "doing"));
        repository.save(Item.create("task6", "user2", "done"));
        return repository;
    }

    @Bean
    public UserRepository inMemoryUserRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public UserManager userManagerMock() {
        return new UserManagerMock("user1");
    }

    @Bean
    public ListItemsUsecase listItemsUsecase(ItemRepository itemRepository) {
        return new ListItemsUsecase(itemRepository);
    }

    @Bean
    public CreateItemUsecase createItemUsecase(UserRepository userRepository, ItemRepository itemRepository) {
        return new CreateItemUsecase(userRepository, itemRepository);
    }
}

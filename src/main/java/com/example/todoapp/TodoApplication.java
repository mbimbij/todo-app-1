package com.example.todoapp;

import com.example.todoapp.core.ChangeItemStateUsecase;
import com.example.todoapp.core.CreateItemUsecase;
import com.example.todoapp.core.DeleteItemsUsecase;
import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.ListItemsUsecase;
import com.example.todoapp.core.UnknownUserException;
import com.example.todoapp.core.User;
import com.example.todoapp.core.UserManager;
import com.example.todoapp.core.UserRepository;
import com.example.todoapp.infra.InMemoryUserRepository;
import com.example.todoapp.infra.UserManagerMock;
import com.example.todoapp.infra.itemrepository.JpaItemRepository;
import com.example.todoapp.infra.itemrepository.JpaItemRepositoryInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

//    @Bean
//    public ItemRepository inMemoryItemRepository() {
//        InMemoryItemRepository repository = new InMemoryItemRepository();
//        repository.save(Item.create("task1", "user1", "todo"));
//        repository.save(Item.create("task2", "user1", "doing"));
//        repository.save(Item.create("task3", "user1", "done"));
//        repository.save(Item.create("UUtask4", "user", "todo"));
//        repository.save(Item.create("UUtask5", "user", "doing"));
//        repository.save(Item.create("UUtask6", "user", "done"));
//        return repository;
//    }

    @Bean
    public ItemRepository jpaItemRepository(JpaItemRepositoryInterface jpaRepository) {
        ItemRepository repository = new JpaItemRepository(jpaRepository);
        repository.save(Item.create("anon-task1", "anonymous", "todo"));
        repository.save(Item.create("anon-task2", "anonymous", "doing"));
        repository.save(Item.create("anon-task3", "anonymous", "done"));
        repository.save(Item.create("jpaUUtask4", "user", "todo"));
        repository.save(Item.create("jpaUUtask5", "user", "doing"));
        repository.save(Item.create("jpaUUtask6", "user", "done"));
        return repository;
    }

    @Bean
    public UserRepository inMemoryUserRepository() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        repository.save(User.createWithName("anonymous"));
        repository.save(User.createWithName("user1"));
        repository.save(User.createWithName("user"));
        return repository;
    }

    @Bean
    public UserManager userManagerMock(UserRepository userRepository) {
        String username = "user1";
        userRepository.getByUsername(username).orElseThrow(() -> new UnknownUserException("unknown user: "+username));
        return new UserManagerMock();
    }

    @Bean
    public ListItemsUsecase listItemsUsecase(ItemRepository itemRepository) {
        return new ListItemsUsecase(itemRepository);
    }

    @Bean
    public CreateItemUsecase createItemUsecase(UserRepository userRepository, ItemRepository itemRepository) {
        return new CreateItemUsecase(userRepository, itemRepository);
    }

    @Bean
    public DeleteItemsUsecase deleteItemsUsecase(ItemRepository itemRepository){
        return new DeleteItemsUsecase(itemRepository);
    }

    @Bean
    public ChangeItemStateUsecase changeStateUsecase(ItemRepository itemRepository){
        return new ChangeItemStateUsecase(itemRepository);
    }
}

package com.example.todoapp;

import com.example.todoapp.core.ChangeItemStateUsecase;
import com.example.todoapp.core.CreateItemUsecase;
import com.example.todoapp.core.DeleteItemsUsecase;
import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.ListItemsUsecase;
import com.example.todoapp.core.User;
import com.example.todoapp.core.UserManager;
import com.example.todoapp.core.UserRepository;
import com.example.todoapp.infra.usermanagement.JpaUserManager;
import com.example.todoapp.infra.itemrepository.JpaItemRepository;
import com.example.todoapp.infra.itemrepository.JpaItemRepositoryInterface;
import com.example.todoapp.infra.usermanagement.JpaUserRepository;
import com.example.todoapp.infra.usermanagement.JpaUserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TodoApplication {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserManager userManager;

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @PostConstruct
    private void init(){
        initUsers();
        initItems();
    }

    private void initItems() {
        itemRepository.save(Item.create("anon-task1", "anonymous", "todo"));
        itemRepository.save(Item.create("anon-task2", "anonymous", "doing"));
        itemRepository.save(Item.create("anon-task3", "anonymous", "done"));
        itemRepository.save(Item.create("jpaUUtask4", "user", "todo"));
        itemRepository.save(Item.create("jpaUUtask5", "user", "doing"));
        itemRepository.save(Item.create("jpaUUtask6", "user", "done"));
        itemRepository.save(Item.create("taskMario1", "mario", "todo"));
        itemRepository.save(Item.create("taskMario2", "mario", "doing"));
    }

    private void initUsers() {
        User anonymous = User.createWithName("anonymous");
        userRepository.save(anonymous);
        User user1 = User.createWithName("user1");
        userRepository.save(user1);
        userManager.setLoggedInUser(user1);
        userManager.setPassword("pass");

        User user = User.createWithName("user");
        userRepository.save(user);
        userManager.setLoggedInUser(user);
        userManager.setPassword("pass");

        User mario = User.createWithName("mario");
        userRepository.save(mario);
        userManager.setLoggedInUser(mario);
        userManager.setPassword("mario");

        userManager.setLoggedInUser(anonymous);
    }

    @Bean
    public UserManager userManager(JpaUserRepositoryInterface jpaItemRepository){
        return new JpaUserManager(jpaItemRepository);
    }

    @Bean
    public ItemRepository jpaItemRepository(JpaItemRepositoryInterface jpaRepository) {
        return new JpaItemRepository(jpaRepository);
    }

    @Bean
    public UserRepository userRepository(JpaUserRepositoryInterface jpaUserRepositoryInterface) {
        return new JpaUserRepository(jpaUserRepositoryInterface);
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

package com.example.todoapp;

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
	public ItemRepository inMemoryItemRepository(){
		return new InMemoryItemRepository();
	}

	@Bean
	public UserRepository inMemoryUserRepository(){
		return new InMemoryUserRepository();
	}

	@Bean
	public UserManager userManagerMock(){
		return new UserManagerMock("user1");
	}

	@Bean
	public ListItemsUsecase presentItemsUsecase(ItemRepository itemRepository){
		return new ListItemsUsecase(itemRepository);
	}
}

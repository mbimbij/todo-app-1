package com.example.todoapp.core;

import java.util.List;

public interface ItemRepository {
    void save(Item item);

    List<Item> getItemsForUser(String user);
}

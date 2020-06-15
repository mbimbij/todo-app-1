package com.example.todoapp.core;

import java.util.Collection;
import java.util.List;

public interface ItemRepository {
    Item save(Item item);

    void deleteAll();

    List<Item> getItemsForUser(String user);

    void deleteAllForUser(User user);

    void deleteByIds(Collection<String> itemIds);
}

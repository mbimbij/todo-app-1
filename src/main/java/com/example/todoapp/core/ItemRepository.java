package com.example.todoapp.core;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item save(Item item);

    void deleteAll();

    List<Item> getItemsForUser(String user);

    void deleteAllForUser(User user);

    void deleteByIds(Collection<String> itemIds);

    void deleteById(String itemId);

    boolean exists(String itemId);

    Optional<Item> getById(String itemId);

    Item upsert(Item updatedItem);
}

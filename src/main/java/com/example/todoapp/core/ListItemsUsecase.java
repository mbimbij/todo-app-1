package com.example.todoapp.core;

import java.util.List;
import java.util.stream.Collectors;

public class ListItemsUsecase {

    private ItemRepository itemRepository;

    public ListItemsUsecase(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemPresentation> presentItemsForUser(User user) {
        String userId=user.getId();
        List<Item> items = itemRepository.getItemsForUser(userId);
        return items.stream()
                .map(ItemPresentation::createFromItem)
                .collect(Collectors.toList());
    }
}

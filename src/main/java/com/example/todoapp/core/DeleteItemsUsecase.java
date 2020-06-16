package com.example.todoapp.core;

import java.util.Collection;

public class DeleteItemsUsecase {
    private ItemRepository itemRepository;

    public DeleteItemsUsecase(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void deleteByIds(Collection<String> itemIds) {
        itemRepository.deleteByIds(itemIds);
    }

    public void deleteById(String itemId) {
        itemRepository.deleteById(itemId);
    }
}

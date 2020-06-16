package com.example.todoapp.core;

public class ChangeItemStateUsecase {
    private ItemRepository itemRepository;

    public ChangeItemStateUsecase(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item changeState(String itemId, String newState) {
        Item updatedItem = itemRepository.getById(itemId)
                .map(Item::copy)
                .map(item -> {
                    item.setState(newState);
                    return item;
                })
                .orElseThrow(() -> {
                    String errorMessage = String.format("item %s does not exist", itemId);
                    return new ItemDoesNotExistException(errorMessage);
                });
        updatedItem = itemRepository.upsert(updatedItem);
        return updatedItem;
    }
}

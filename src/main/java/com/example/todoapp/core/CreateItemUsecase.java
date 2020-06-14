package com.example.todoapp.core;

public class CreateItemUsecase {
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    public CreateItemUsecase(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public ItemPresentation createItem(CreateItemInput createItemInput) {
        String userId = createItemInput.getUserId();
        String name = createItemInput.getName();
        String state = createItemInput.getState();
        if(!userRepository.exists(userId)){
            throw new UnknownUserException();
        }
        Item newItem = itemRepository.save(Item.create(name, userId, state));
        return ItemPresentation.createFromItem(newItem);
    }
}

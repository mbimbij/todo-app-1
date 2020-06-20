package com.example.todoapp.core;

public class CreateItemUsecase {
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    public CreateItemUsecase(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public CreateItemOutput createItem(CreateItemInput createItemInput) {
        String userName = createItemInput.getUserName();
        String itemName = createItemInput.getName();
        String state = createItemInput.getState();
        if(!userRepository.existsByName(userName)){
            throw new UnknownUserException("unknown user with name: "+userName);
        }
        Item newItem = itemRepository.save(Item.create(itemName, userName, state));
        return CreateItemOutput.createFromItem(newItem);
    }
}

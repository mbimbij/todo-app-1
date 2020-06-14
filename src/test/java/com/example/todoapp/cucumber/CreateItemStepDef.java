package com.example.todoapp.cucumber;

import com.example.todoapp.core.CreateItemInput;
import com.example.todoapp.core.ItemPresentation;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.ListItemsUsecase;
import com.example.todoapp.core.User;
import com.example.todoapp.core.CreateItemUsecase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.fail;

public class CreateItemStepDef {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CreateItemUsecase usecase;

    @Autowired
    private ListItemsUsecase listItemsUsecase;

    @Given("no items for \"{user}\"")
    public void noItemsFor(User user) {
        itemRepository.deleteAllForUser(user);
    }

    @When("\"{user}\" creates the item")
    public void createsTheItem(User user, List<Map<String, String>> maps) {
        Map<String, String> newItemData = maps.get(0);
        CreateItemInput createItemInput = new CreateItemInput(user.getId(), newItemData.get("name"), newItemData.get("state"));
        usecase.createItem(createItemInput);
        System.out.println();
    }

    @Then("presented items for \"{user}\" are")
    public void presentedItemsForUserAre(User user, DataTable expectedItemList) throws Throwable {
        List<ItemPresentation> itemPresentations = listItemsUsecase.presentItemsForUser(user);
        DataTable actualItemList = TypeConverters.toDataTable(itemPresentations);
        actualItemList.unorderedDiff(expectedItemList);
    }
}

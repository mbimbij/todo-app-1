package com.example.todoapp.cucumber;

import com.example.todoapp.core.ItemPresentation;
import com.example.todoapp.core.ListItemsUsecase;
import com.example.todoapp.core.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PresentItemsUsecaseStepDef {
    private ListItemsUsecase usecase;

    private CucumberContext context;

    public PresentItemsUsecaseStepDef(ListItemsUsecase usecase, CucumberContext context) {
        this.usecase = usecase;
        this.context = context;
    }

    @Then("presented items for user \"{user}\" are")
    public void presentedItemsForUserAre(User user, DataTable expectedItemPresentations) {
        List<ItemPresentation> itemPresentations = usecase.presentItemsForUser(user);
        DataTable actualItemPresentations = convertItemPresentationsToCucumberDatatable(itemPresentations);
        actualItemPresentations.unorderedDiff(expectedItemPresentations);
    }

    private DataTable convertItemPresentationsToCucumberDatatable(List<ItemPresentation> itemPresentations) {
        List<List<String>> listOfLists = new ArrayList<>();
        listOfLists.add(Arrays.asList("name", "state"));
        List<List<String>> itemPresentationsAsListOfLists = itemPresentations.stream()
                .map(itemPresentation -> Arrays.asList(itemPresentation.getName(), itemPresentation.getState()))
                .collect(Collectors.toList());
        listOfLists.addAll(itemPresentationsAsListOfLists);
        return DataTable.create(listOfLists);
    }
}

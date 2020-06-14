package com.example.todoapp.cucumber;

import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemPresentation;
import com.example.todoapp.core.ListItemsResponseModel;
import com.example.todoapp.core.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TypeConverters {
    @DataTableType
    public Item item(Map<String, String> map) {
        return Item.create(map.get("name"), map.get("owner"), map.get("state"));
    }

    @DataTableType
    public ItemPresentation itemPresentation(Map<String, String> map) {
        return ItemPresentation.create(map.get("name"), map.get("state"));
    }

    @ParameterType(".*")
    public User user(String userId) {
        return User.createWithId(userId);
    }

    public static DataTable toDataTable(ListItemsResponseModel listItemsResponseModel) {
        List<List<String>> result = new ArrayList<>();
        List<String> header = List.of("name", "state");
        result.add(header);
        List<List<String>> itemPresentationsAsListofLists = listItemsResponseModel.items.stream()
                .map(itemPresentation -> List.of(itemPresentation.getName(), itemPresentation.getState()))
                .collect(Collectors.toList());
        result.addAll(itemPresentationsAsListofLists);
        return DataTable.create(result);
    }
}

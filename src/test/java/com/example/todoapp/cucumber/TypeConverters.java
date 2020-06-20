package com.example.todoapp.cucumber;

import com.example.todoapp.core.ChangeItemStateOutput;
import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemPresentation;
import com.example.todoapp.core.ListItemsResponseModel;
import com.example.todoapp.core.UnknownUserException;
import com.example.todoapp.core.User;
import com.example.todoapp.core.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TypeConverters {
    @Autowired
    private UserRepository userRepository;

    @DataTableType
    public Item item(Map<String, String> map) {
//        return Item.create(map.get("name"), map.get("owner"), map.get("state"));
        map.putIfAbsent("id", UUID.randomUUID().toString());
        return new ObjectMapper().convertValue(map, Item.class);
    }

    @DataTableType
    public ItemPresentation itemPresentation(Map<String, String> map) {
        return ItemPresentation.create(map.get("name"), map.get("state"));
    }

    @ParameterType(".*")
    public User user(String userName) {
        return userRepository.getByUsername(userName).orElseThrow(() -> new UnknownUserException(userName));
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

    public static DataTable toDataTable(List<ItemPresentation> itemPresentations) {
        List<List<String>> result = new ArrayList<>();
        List<String> header = List.of("name", "state");
        result.add(header);
        List<List<String>> itemPresentationsAsListofLists = itemPresentations.stream()
                .map(itemPresentation -> List.of(itemPresentation.getName(), itemPresentation.getState()))
                .collect(Collectors.toList());
        result.addAll(itemPresentationsAsListofLists);
        return DataTable.create(result);
    }

    @DataTableType
    public TestInput testInput(Map<String, String> map) {
        return new ObjectMapper().convertValue(map, TestInput.class);
    }

    @Data
    public static class TestInput {
        private String name;
        private String state;
    }

    @DataTableType
    public ChangeItemStateOutput changeItemStateOutput(Map<String, String> map){
        return new ChangeItemStateOutput(map.get("id"),map.get("name"),map.get("state"));
    }
}

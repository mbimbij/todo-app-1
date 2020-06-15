package com.example.todoapp.cucumber;

import com.example.todoapp.core.CreateItemInput;
import com.example.todoapp.core.CreateItemOutput;
import com.example.todoapp.core.ItemPresentation;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.ListItemsUsecase;
import com.example.todoapp.core.User;
import com.example.todoapp.core.CreateItemUsecase;
import com.example.todoapp.cucumber.TypeConverters.TestInput;
import com.example.todoapp.infra.CreateItemInputRest;
import com.example.todoapp.infra.CreateItemOutputRest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CreateItemStepDef {

    public static class UsecaseTest {
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
            expectedItemList.unorderedDiff(actualItemList);
        }
    }

    public static class RestControllerTest {
        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        private CreateItemOutputRest actualItemOutputData;

        @When("\"{user}\" creates the item through rest controller")
        public void createsTheItemThroughRestController(User user, TestInput restInput) {
            String url = String.format("http://localhost:%d/createItem", port);
            String newItemName = restInput.getName();
            String newItemState = restInput.getState();
            CreateItemInputRest createItemInput = new CreateItemInputRest(newItemName, newItemState);
            ResponseEntity<CreateItemOutputRest> responseEntity = restTemplate.postForEntity(url, createItemInput, CreateItemOutputRest.class);
            assertThat(responseEntity.getStatusCodeValue()).isBetween(200,299);
            actualItemOutputData = responseEntity.getBody();
        }

        @Then("response is")
        public void responseIs(TestInput expected) {
            SoftAssertions.assertSoftly(softAssertions -> {
                softAssertions.assertThat(actualItemOutputData.getName()).isEqualTo(expected.getName());
                softAssertions.assertThat(actualItemOutputData.getState()).isEqualTo(expected.getState());
            });
        }
    }
}

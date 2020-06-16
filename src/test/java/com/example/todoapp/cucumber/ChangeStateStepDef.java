package com.example.todoapp.cucumber;

import com.example.todoapp.core.ChangeItemStateUsecase;
import com.example.todoapp.core.ChangeItemStateOutput;
import com.example.todoapp.core.User;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeStateStepDef {
    public static class UsecaseTest {
        @Autowired
        private ChangeItemStateUsecase usecase;

        @When("\"{user}\" changes the state of item {string} to {string}")
        public void changesTheStateOfItemTo(User user, String itemId, String newState) {
            usecase.changeState(itemId, newState);
        }
    }

    public static class RestInterfaceTest {
        @LocalServerPort
        private int port;
        @Autowired
        private TestRestTemplate restTemplate;
        private ChangeItemStateOutput actualOutput;

        @When("\"{user}\" changes the state of item {string} to {string} through rest interface")
        public void changesTheStateOfItemToThroughRestInterface(User user, String itemId, String newState) {
            ResponseEntity<ChangeItemStateOutput> entity = restTemplate.postForEntity(
                    String.format("http://localhost:%d/item/%s/changeState/%s", port, itemId, newState),
                    null,
                    ChangeItemStateOutput.class);
            assertThat(entity.getStatusCodeValue()).isBetween(200,299);
            actualOutput = entity.getBody();
        }

        @Then("change state returned value is")
        public void changeStateReturnedValueIs(ChangeItemStateOutput expectedOutput) {
            assertThat(actualOutput).isEqualToComparingFieldByField(expectedOutput);
        }
    }
}

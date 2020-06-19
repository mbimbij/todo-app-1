package com.example.todoapp.infra;

import com.example.todoapp.core.ChangeItemStateOutput;
import com.example.todoapp.core.ChangeItemStateUsecase;
import com.example.todoapp.core.CreateItemInput;
import com.example.todoapp.core.CreateItemOutput;
import com.example.todoapp.core.CreateItemUsecase;
import com.example.todoapp.core.DeleteItemsUsecase;
import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemPresentation;
import com.example.todoapp.core.ListItemsResponseModel;
import com.example.todoapp.core.ListItemsUsecase;
import com.example.todoapp.core.User;
import com.example.todoapp.core.UserManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Component
public class AppRestController {
    @Autowired
    private UserManager userManager;

    private boolean isUserNotLoggedIn(Principal principal){
        return principal == null || StringUtils.isBlank(principal.getName());
    }

    @RestController
    public class ListItemsRestController {
        @Autowired
        private ListItemsUsecase usecase;

        @GetMapping("/listItems")
        public ListItemsResponseModel listItems(Principal principal) {
            String username = isUserNotLoggedIn(principal) ? "user1" : principal.getName();
            userManager.setLoggedInUser(User.createWithId(username));
            List<ItemPresentation> itemPresentations = usecase.presentItemsForUser(userManager.getLoggedInUser());
            ListItemsResponseModel response = new ListItemsResponseModel();
            response.items = itemPresentations;
            return response;
        }
    }

    @RestController
    public class CreateItemRestController {
        @Autowired
        private CreateItemUsecase usecase;

        @PostMapping("/createItem")
        public CreateItemOutputRest createItem(@RequestBody CreateItemInputRest inputRest) {
            CreateItemInput input = mapInput(inputRest);
            CreateItemOutput output = usecase.createItem(input);
            return mapOutput(output);
        }

        private CreateItemOutputRest mapOutput(CreateItemOutput output) {
            return new CreateItemOutputRest(output.getId(), output.getName(), output.getState());
        }

        private CreateItemInput mapInput(@RequestBody CreateItemInputRest inputRest) {
            return new CreateItemInput(userManager.getLoggedInUser().getId(), inputRest.getName(), inputRest.getState());
        }
    }

    @RestController
    public class DeleteItemRestController {
        @Autowired
        private DeleteItemsUsecase usecase;

        @PostMapping("/deleteItems")
        public void deleteItems(@RequestBody List<String> itemIds) {
            usecase.deleteByIds(itemIds);
        }

        @DeleteMapping("/item/{itemId}")
        public void deleteItem(@PathVariable("itemId") String itemId) {
            usecase.deleteById(itemId);
        }
    }

    @RestController
    public class ChangeItemStateRestController {
        @Autowired
        private ChangeItemStateUsecase usecase;

        @PostMapping("/item/{itemId}/changeState/{newState}")
        public ChangeItemStateOutput changeItemState(@PathVariable("itemId") String itemId,
                                                     @PathVariable("newState") String newState){
            Item updatedItem = usecase.changeState(itemId, newState);
            return ChangeItemStateOutput.createFromItem(updatedItem);
        }
    }
}

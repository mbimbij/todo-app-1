package com.example.todoapp.infra;

import com.example.todoapp.core.CreateItemInput;
import com.example.todoapp.core.CreateItemOutput;
import com.example.todoapp.core.CreateItemUsecase;
import com.example.todoapp.core.ItemPresentation;
import com.example.todoapp.core.ListItemsResponseModel;
import com.example.todoapp.core.ListItemsUsecase;
import com.example.todoapp.core.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
public class AppRestController {
    @Autowired
    private UserManager userManager;

    @RestController
    public class ListItemsRestController {
        @Autowired
        private ListItemsUsecase usecase;

        @GetMapping("/listItems")
        public ListItemsResponseModel listItems() {
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
            CreateItemOutput output = usecase.createItem(new CreateItemInput(userManager.getLoggedInUser().getId(), inputRest.getName(), inputRest.getState()));
            return new CreateItemOutputRest(output.getName(), output.getState());
        }
    }
}

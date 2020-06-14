package com.example.todoapp.infra;

import com.example.todoapp.core.ItemPresentation;
import com.example.todoapp.core.ListItemsResponseModel;
import com.example.todoapp.core.ListItemsUsecase;
import com.example.todoapp.core.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppRestController {

    @Autowired
    private ListItemsUsecase usecase;

    @Autowired
    private UserManager userManager;

    @GetMapping("/listItems")
    public ListItemsResponseModel listItems(){
        List<ItemPresentation> itemPresentations = usecase.presentItemsForUser(userManager.getLoggedInUser());
        ListItemsResponseModel response = new ListItemsResponseModel();
        response.items = itemPresentations;
        return response;
    }
}

package com.example.todoapp.cucumber;

import com.example.todoapp.core.ItemPresentation;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class Utils {
    public static void verifyItemPresentationIdsNotBlank(Collection<ItemPresentation> itemPresentations){
        assertThat(itemPresentations)
                .as("id of presented items should not be blank")
                .allMatch(itemPresentation -> StringUtils.isNotBlank(itemPresentation.getId()));
    }
}

/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.stingion.makeitfine.data.graphql.service.GQItemService;
import com.stingion.makeitfine.data.model.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemMutation implements GraphQLMutationResolver {

    private final GQItemService itemService;

    public Item createItem(String header, Double price) {
        return itemService.createItem(header, price);
    }
}

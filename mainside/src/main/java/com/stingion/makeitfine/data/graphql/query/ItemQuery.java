/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.stingion.makeitfine.data.graphql.service.GQItemService;
import com.stingion.makeitfine.data.model.Item;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemQuery implements GraphQLQueryResolver {

    private final GQItemService itemService;

    public List<Item> getItems(int count) {
        return itemService.getItems(count);
    }

    public Optional<Item> getItem(int id) {
        return itemService.getItem(id);
    }
}

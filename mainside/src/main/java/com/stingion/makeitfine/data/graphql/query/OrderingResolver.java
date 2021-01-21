/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.query;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.stingion.makeitfine.data.graphql.service.GQItemService;
import com.stingion.makeitfine.data.model.Item;
import com.stingion.makeitfine.data.model.Ordering;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderingResolver implements GraphQLResolver<Ordering> {

    @Autowired
    private GQItemService itemService;

    public @Nullable Item getItem(Ordering order) {
        return itemService.getItem(order.getItem().getId()).orElse(null);
    }
}

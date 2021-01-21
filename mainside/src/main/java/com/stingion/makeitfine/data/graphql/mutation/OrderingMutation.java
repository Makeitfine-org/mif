/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.stingion.makeitfine.data.graphql.service.GQOrderingService;
import com.stingion.makeitfine.data.model.Ordering;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderingMutation implements GraphQLMutationResolver {

    private final GQOrderingService orderingService;

    public Ordering createOrder(String description, String status, Integer itemId) {
        return orderingService.createOrder(description, status, itemId);
    }
}

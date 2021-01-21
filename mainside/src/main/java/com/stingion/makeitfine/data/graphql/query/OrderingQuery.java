/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.stingion.makeitfine.data.graphql.service.GQOrderingService;
import com.stingion.makeitfine.data.model.Ordering;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderingQuery implements GraphQLQueryResolver {

    private final GQOrderingService orderingService;

    public List<Ordering> getOrders(int count) {
        return orderingService.getOrders(count);
    }

    public Optional<Ordering> getOrder(int id) {
        return orderingService.getOrder(id);
    }
}

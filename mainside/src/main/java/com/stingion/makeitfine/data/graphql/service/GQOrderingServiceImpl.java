/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.service;

import com.stingion.makeitfine.data.model.Item;
import com.stingion.makeitfine.data.model.Ordering;
import com.stingion.makeitfine.data.model.utils.OrderingStatus;
import com.stingion.makeitfine.data.repository.OrderingRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GQOrderingServiceImpl extends GQEntityServiceImpl<Ordering> implements GQOrderingService {

    private final OrderingRepository orderingRepository;

    public Ordering createOrder(String description, String status, Integer itemId) {
        Ordering order = new Ordering();
        order.setDescription(description);
        order.setStatus(OrderingStatus.valueOf(status));

        Item item = new Item();
        item.setId(itemId);

        order.setItem(item);

        return orderingRepository.save(order);
    }

    @Override
    public List<Ordering> getOrders(int count) {
        return getEntities(count);
    }

    @Override
    public Optional<Ordering> getOrder(int id) {
        return getEntity(id);
    }
}

/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.service;

import com.stingion.makeitfine.data.model.Ordering;
import java.util.List;
import java.util.Optional;

public interface GQOrderingService {

    Ordering createOrder(String description, String status, Integer itemId);

    List<Ordering> getOrders(int count);

    Optional<Ordering> getOrder(int id);
}

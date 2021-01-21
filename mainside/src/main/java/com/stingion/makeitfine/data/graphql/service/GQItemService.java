/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.service;

import com.stingion.makeitfine.data.model.Item;
import java.util.List;
import java.util.Optional;

public interface GQItemService {

    Item createItem(String header, Double price);

    List<Item> getItems(int count);

    Optional<Item> getItem(int id);
}

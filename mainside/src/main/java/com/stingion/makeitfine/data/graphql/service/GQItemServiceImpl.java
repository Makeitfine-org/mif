/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.service;

import com.stingion.makeitfine.data.model.Item;
import com.stingion.makeitfine.data.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GQItemServiceImpl extends GQEntityServiceImpl<Item> implements GQItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item createItem(String header, Double price) {
        return itemRepository.save(new Item(header, price));
    }

    @Override
    public List<Item> getItems(int count) {
        return getEntities(count);
    }

    @Override
    public Optional<Item> getItem(int id) {
        return getEntity(id);
    }
}

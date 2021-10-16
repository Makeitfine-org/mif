/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.model.utils;

import java.util.Arrays;
import java.util.NoSuchElementException;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

/**
 * Created by joe on 11.06.17.
 */
public class AttrConverter {

    @Convert
    public static class StateConvertor implements AttributeConverter<State, String> {

        @Override
        public String convertToDatabaseColumn(State attribute) {
            return attribute.getState();
        }

        @SuppressWarnings("method.invocation.invalid")
        @Override
        public State convertToEntityAttribute(String dbData) {
            return Arrays.stream(State.values())
                    .filter(s -> s.getState().equalsIgnoreCase(dbData))
                    .findAny()
                    .orElseThrow(() -> new NoSuchElementException(String.format("Not found attribute: %s", dbData)));
        }
    }
}

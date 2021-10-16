/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.model.utils;

public enum State {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    LOCKED("Locked");

    private String state;

    State(final String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return this.state;
    }
}

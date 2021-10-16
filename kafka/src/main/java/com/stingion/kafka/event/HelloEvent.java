/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.kafka.event;

import com.stingion.util.mq.Message;
import java.io.Serializable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HelloEvent extends Message implements Serializable {

    private static final long serialVersionUID = 5694255628125164265L;

    public HelloEvent(String message) {
        super(message);
    }
}

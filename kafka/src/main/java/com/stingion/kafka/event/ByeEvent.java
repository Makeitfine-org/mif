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
public class ByeEvent extends Message implements Serializable {

    private static final long serialVersionUID = 3487525513239619559L;

    public ByeEvent(String message) {
        super(message);
    }
}

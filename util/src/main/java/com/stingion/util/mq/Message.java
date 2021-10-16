/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.util.mq;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.checkerframework.checker.nullness.qual.NonNull;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = 540827535458245848L;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    private String msg;

    private String localDateTime;

    public Message(@NonNull String msg) {
        this.msg = msg;
        this.localDateTime = DATE_TIME_FORMATTER.format(LocalDateTime.now());
    }
}

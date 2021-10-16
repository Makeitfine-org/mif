/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.intro.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"key", "details"})
@Document(collection = "info")
@NoArgsConstructor
public class Info {

    @Id
    private String id;

    @Indexed(name = "key_1", unique = true)
    private String key;
    private String details;

    public Info(String key, String details) {
        this.key = key;
        this.details = details;
    }
}

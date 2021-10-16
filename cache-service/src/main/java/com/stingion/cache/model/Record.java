/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.cache.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
public class Record implements Serializable {

    private static final long serialVersionUID = -5147594615580603982L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_RECORD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private Long id;

    private String msg;

    public Record(String msg) {
        this.msg = msg;
    }
}

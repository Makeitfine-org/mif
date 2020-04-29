/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.model;

import com.stingion.makeitfine.data.model.utils.OrderingStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created on 12.06.17.
 */
@Entity
@Table(name = "ORDERING")
//@EqualsAndHashCode(of = "id")
@ToString(exclude = {"item", "creditCard"})
@Getter
@Setter
@NoArgsConstructor
public class Ordering {

    private static final int PRIME_NUMBER = 31;

    @TableGenerator(
            name = "Ordering_gen",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "ORDERING_SEQUENCE",
            allocationSize = 1)
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Ordering_gen")
    private Integer id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(columnDefinition = "enum('Pending','Performed','Cancelled')")
    @Enumerated(EnumType.STRING)
    private OrderingStatus status;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "CREDIT_CARD_ID")
    private CreditCard creditCard;

    @Override
    @SuppressWarnings("CPD-START")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ordering ordering = (Ordering) o;

        return id.equals(ordering.id);
    }

    // If you tell Hibernate to generate your primary key values, you need to use a fixed hash code,
    // https://developer.jboss.org/wiki/EqualsAndHashCode?_sscc=t
    // https://thoughts-on-java.org/ultimate-guide-to-implementing-equals-and-hashcode-with-hibernate/
    @Override
    @SuppressWarnings("CPD-END")
    public int hashCode() {
        return PRIME_NUMBER;
    }
}

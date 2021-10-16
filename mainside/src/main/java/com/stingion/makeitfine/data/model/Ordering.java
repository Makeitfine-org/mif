/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.stingion.makeitfine.data.model.utils.OrderingStatus;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nullable;
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
    @JsonProperty(access = Access.WRITE_ONLY)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "CREDIT_CARD_ID")
    @JsonProperty(access = Access.WRITE_ONLY)
    private CreditCard creditCard;

    @Override
    @SuppressWarnings("CPD-START")
    @SuppressFBWarnings("NP_METHOD_PARAMETER_TIGHTENS_ANNOTATION")
    public boolean equals(@Nullable Object o) {
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

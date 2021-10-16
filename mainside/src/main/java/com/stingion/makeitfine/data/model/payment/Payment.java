/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.model.payment;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created on 12.06.17.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "PAYMENT")
//@EqualsAndHashCode(of = {"id"})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PAYMENT_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Undefined")
public class Payment {

    private static final int PRIME_NUMBER = 37;

    @TableGenerator(
            name = "Payment_gen",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "PAYMENT_SEQUENCE",
            allocationSize = 1)
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Payment_gen")
    private int id;

    @Column(name = "AMOUNT")
    private Double amount;

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

        Payment payment = (Payment) o;

        return id == payment.id;
    }

    // See: Ordering hashcode method comment
    @Override
    @SuppressWarnings("CPD-END")
    public int hashCode() {
        return PRIME_NUMBER;
    }
}

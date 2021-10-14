/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stingion.makeitfine.data.model.utils.ModelConstants;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created on 12.06.17.
 */
@Entity
@Table(name = "ITEM")
@EqualsAndHashCode(of = "header")
@ToString(exclude = {"orders"})
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @TableGenerator(
            name = "Item_gen",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "ITEM_SEQUENCE",
            allocationSize = 1)
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Item_gen")
    private Integer id;

    @Column(name = "HEADER", unique = true, nullable = false)
    @Size(min = ModelConstants.MIN_ITEM_LENGTH)
    private String header;

    @Column(name = "PRICE")
    private Double price;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Ordering> orders;

    public Item(String header, Double price) {
        this.header = header;
        this.price = price;
    }
}

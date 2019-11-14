/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.model;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * Created on 12.06.17.
 */
@Entity
@Table(name = "BANK")
@EqualsAndHashCode(of = "name")
@ToString(exclude = {"creditCards"})
@Getter
@Setter
@NoArgsConstructor
public class Bank {

  @TableGenerator(
      name = "Bank_gen",
      table = "SEQUENCES",
      pkColumnName = "SEQ_NAME",
      valueColumnName = "SEQ_NUMBER",
      pkColumnValue = "BANK_SEQUENCE",
      allocationSize = 1)
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "Bank_gen")
  private Integer id;

  @Column(name = "NAME", unique = true)
  @Length(min = ModelConstants.MIN_BANK_NAME_LENGTH, max = ModelConstants.MAX_BANK_NAME_LENGTH)
  private String name;

  @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<CreditCard> creditCards;

  public Bank(String name) {
    this.name = name;
  }
}

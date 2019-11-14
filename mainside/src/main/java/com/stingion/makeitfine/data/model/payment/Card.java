/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.model.payment;

import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/** Created on 12.06.17. */
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("Card")
public class Card extends Payment {}

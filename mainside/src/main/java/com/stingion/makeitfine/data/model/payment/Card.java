/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.model.payment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.ToString;

/**
 * Created on 12.06.17.
 */
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("Card")
public class Card extends Payment {
}

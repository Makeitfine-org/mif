/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.model.user;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "WORKER")
@PrimaryKeyJoinColumn(name = "ID")
@ToString(callSuper = true)
public class Worker extends Contact {
}

/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.repository;

import com.stingion.makeitfine.data.model.payment.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends EntityRepository<Payment> {
}

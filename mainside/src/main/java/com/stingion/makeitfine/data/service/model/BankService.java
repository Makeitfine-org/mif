/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model;

import com.stingion.makeitfine.data.dto.EntityDTO;
import com.stingion.makeitfine.data.model.Bank;
import java.util.List;

public interface BankService extends EntityService<Bank> {

    List<EntityDTO> findBanks(List<Integer> bankIds);
}

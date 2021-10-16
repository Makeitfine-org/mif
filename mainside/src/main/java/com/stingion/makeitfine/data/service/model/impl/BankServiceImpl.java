/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model.impl;

import com.stingion.makeitfine.data.dto.EntityDTO;
import com.stingion.makeitfine.data.model.Bank;
import com.stingion.makeitfine.data.repository.BankRepository;
import com.stingion.makeitfine.data.service.model.BankService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl extends EntityServiceImpl<Bank> implements BankService {

    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<EntityDTO> findBanks(List<Integer> bankIds) {
        return bankRepository.findBanks(bankIds);
    }
}

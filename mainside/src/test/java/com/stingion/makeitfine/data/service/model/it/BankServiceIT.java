/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model.it;

import com.stingion.makeitfine.data.model.Bank;
import com.stingion.makeitfine.data.service.model.BankService;
import com.stingion.makeitfine.testconfiguration.ServiceTestConfiguration;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BankServiceIT extends EntityServiceITAncestor<Bank> {

    @Autowired
    protected ServiceTestConfiguration.EntityTestData<Bank> bankTestData;

    @Autowired
    private BankService bankService;

    @Test
    public void findBanks() {
        Assertions.assertArrayEquals(
                new Integer[]{1, 3, 4},
                bankService.findBanks(Lists.newArrayList(1, 4, 3)).stream().map(b -> b.getId()).toArray(Integer[]::new)
        );
    }
}

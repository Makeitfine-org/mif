/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.repository;

import com.stingion.makeitfine.data.dto.EntityDTO;
import com.stingion.makeitfine.data.model.Bank;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends EntityRepository<Bank>, JpaSpecificationExecutor<Bank> {

    @Query("    select new com.stingion.makeitfine.data.dto.EntityDTO(b.id, b.name)"
            + " from Bank b "
            + " where b.id in (:bankIds)"
            + " order by b.id")
    List<EntityDTO> findBanks(@Param("bankIds") List<Integer> bankIds);
}

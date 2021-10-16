package com.stingion.makeitfine.data.repository

import com.stingion.makeitfine.data.model.Company
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : EntityRepository<Company>, JpaSpecificationExecutor<Company>

package com.stingion.makeitfine.data.service.model.impl

import com.stingion.makeitfine.data.model.Company
import com.stingion.makeitfine.data.service.model.CompanyService
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl : EntityServiceImpl<Company>(), CompanyService

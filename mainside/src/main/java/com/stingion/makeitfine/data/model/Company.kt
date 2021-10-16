package com.stingion.makeitfine.data.model

import lombok.NoArgsConstructor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table
import javax.persistence.TableGenerator


@Entity
@Table(name = "COMPANY")
@NoArgsConstructor
data class Company(

    @TableGenerator(
        name = "Company_gen",
        table = "SEQUENCES",
        pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_NUMBER",
        pkColumnValue = "COMPANY_SEQUENCE",
        allocationSize = 1
    )
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Company_gen")
    var id: Int?,

    @Column(name = "NAME")
    var name: String?,

    @Lob
    @Column(name = "DESCRIPTION", columnDefinition = "text")
    var description: String?
)

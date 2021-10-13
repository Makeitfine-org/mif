/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.controller

import com.stingion.makeitfine.data.model.CreditCard
import com.stingion.makeitfine.data.service.model.CreditCardService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("creditCards")
class CreditCardController(@Autowired val creditCardService: CreditCardService) {
    data class Message(val id: String?, val text: String)

    @GetMapping
    @ApiOperation("Get all creditCards")
    fun listAllCreditCard(): List<CreditCard?>? {
        return creditCardService.findAll()
    }

    @GetMapping("any")
    fun probe(): List<String> = listOf(
            "one",
            "two",
            "three"
    );

    @GetMapping("other")
    fun other(): List<Message> = listOf(
            Message("1", "one"),
            Message("2", "two"),
            Message("3", "three")
    );
}
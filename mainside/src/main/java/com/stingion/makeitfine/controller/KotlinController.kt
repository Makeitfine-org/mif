/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kotlin")
class KotlinController {
    data class Message(val id: String?, val text: String)

    @GetMapping
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
package com.stingion.makeitfine.controller.it

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import com.stingion.makeitfine.data.model.Company
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CompanyControllerIT : ControllerITProvision() {

    private val mapper = ObjectMapper()

    @Test
    fun `get all companies`() {
        val actual = getResponseBody("/company");

        assertTrue(mapper.readValue(actual, List::class.java).isNotEmpty())
    }

    @Test
    fun `get company with id`() {
        val id = 2;

        val actual = getResponseBody("/company/$id");

        assertEquals(mapper.readValue(actual, Company::class.java).id, id)
    }

    @Test
    fun `get company by not exist id`() {
        val id = Int.MAX_VALUE;

        val actual = getResponseBody("/company/$id");

        assertThrows<JsonParseException> {
            mapper.readValue(actual, Company::class.java)
        }

        assertTrue(actual.contains("java.util.NoSuchElementException"))
    }

    @Test
    fun `get company by wrong id format`() {
        val id = "wrong";

        val actual = getResponseBody("/company/$id");

        assertThrows<JsonParseException> {
            mapper.readValue(actual, Company::class.java)
        }

        assertTrue(actual.contains("org.springframework.web.method.annotation.MethodArgumentTypeMismatchException"))
    }
}
package com.stingion.makeitfine.controller.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.stingion.makeitfine.controller.CompanyController
import com.stingion.makeitfine.data.model.Company
import com.stingion.makeitfine.data.service.model.CompanyService
import com.stingion.makeitfine.testconfiguration.UnitTest
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*
import java.util.stream.Collectors

@ExtendWith(SpringExtension::class)
@WebMvcTest(CompanyController::class)
@UnitTest
class CompanyControllerTest {

    companion object {
        val companies = listOf(
            Company(1, "company1", "Some desc 1"),
            Company(2, "company2", "Some desc 2"),
            Company(3, "company3", null)
        )
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var companyService: CompanyService

    private val mapper: ObjectMapper = ObjectMapper()

    @BeforeEach
    fun `before each`() {
        `when`(companyService.findAll()).thenReturn(companies)
        `when`(companyService.findById(1)).thenReturn(companies.get(0))
        `when`(companyService.findById(2)).thenReturn(companies.get(1))
        `when`(companyService.findById(3)).thenReturn(companies.get(2))

        val idShouldBePositiveException = IllegalArgumentException("id should be positive")

        Mockito.doThrow(idShouldBePositiveException)
            .`when`(companyService)
            .findById(ArgumentMatchers.argThat(NumberIsNotPositive()))
    }

    @Test
    fun `list all companies`() {
        mockMvcByUrlAndContents(
            "/company",
            mapper.writeValueAsString(Company(1, "company1", "Some desc 1")), "2", "3"
        )

        verify(companyService, Mockito.only()).findAll()
    }

    @Test
    fun `get company by id`() {
        val id = 3

        mockMvcByUrlAndContents(
            "/company/$id",
            mapper.writeValueAsString(Company(3, "company3", null))
        )

        verify(companyService, Mockito.only()).findById(id)
    }

    @Test
    fun `get company by id no company`() {
        val id = 4

        mockMvc
            .perform(MockMvcRequestBuilders.get("/company/$id"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(""))

        verify(companyService, Mockito.only()).findById(id)
    }

    @Test
    fun `get company by wrong id format (fail)`() {
        val ret = mockMvc
            .perform(MockMvcRequestBuilders.get("/company/wrong"))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()

        assertEquals(NumberFormatException::class.java, ret.resolvedException!!.cause!!.javaClass)

        Assertions.assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            ret.response.contentAsByteArray[0]
        }

        verify(companyService, Mockito.never()).findById(any())
    }

    @Throws(Exception::class)
    private fun mockMvcByUrlAndContents(url: String, vararg content: String) {
        val matchers = Arrays.stream(content)
            .map { e: String? -> Matchers.containsString(e) }.collect(Collectors.toList())

        mockMvc
            .perform(MockMvcRequestBuilders.get(url))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(Matchers.allOf(matchers)))
    }

    private class NumberIsNotPositive : ArgumentMatcher<Int> {
        override fun matches(argument: Int) = argument < 1
    }
}

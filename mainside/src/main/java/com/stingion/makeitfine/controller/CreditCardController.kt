/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.controller

import com.fasterxml.jackson.annotation.JsonProperty
import com.stingion.makeitfine.data.service.model.CreditCardService
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.abs

@RestController
@RequestMapping("creditCards")
@Api(tags = ["creditCardsController"])
class CreditCardController(@Autowired val creditCardService: CreditCardService) {
    companion object {
        val ID = "id"
        val DIGIT_NUMBER = "digitNumber"
        val INVERSE_DIGITS = "inverseDigits"
    }

    data class EntityVal(val id: Int?, val mainProperty: String?)

    open class CreditCardIdAndNumber(val id: Int?) {
        private val digitNumber: Int = if (id != null) {
            if (id < 0) id.toString().length - 1 else id.toString().length
        } else throw NullPointerException()
            @JsonProperty("digitNumber")
            get() = field


        override fun toString() = "$ID = $id, $DIGIT_NUMBER = $digitNumber"
    }

    interface minAndMax {
        fun min(): String

        fun max(): String
    }

    @SuppressFBWarnings("SA_LOCAL_SELF_ASSIGNMENT")
    class CreditCardModern(id: Int?) : CreditCardIdAndNumber(id), minAndMax {
        private val inverseDigits: Int = if (id != null) {
            StringBuilder(abs(id).toString()).reverse().toString().toInt()
        } else throw NullPointerException()
            @JsonProperty("inverseDigits")
            get() = field

        private var secretMessage: String = ""
            @JsonProperty("secret Message")
            get() = field

        constructor(id: Int?, message: String) : this(id) {
            secretMessage = message
        }

        override fun min() = Int.MIN_VALUE.toString()

        override fun max() = Int.MAX_VALUE.toString()

        override fun toString(): String {
            return super.toString() + ", $INVERSE_DIGITS = $inverseDigits" +
                    " min = ${min()}, max = ${max()}"
        }
    }

    @GetMapping
    @ApiOperation("Get all creditCards")
    fun listAllCreditCard() = creditCardService.findAll()

    @GetMapping("{id}")
    @ApiOperation("Get creditCard by Id")
    fun getCreditCard(@PathVariable
                      @ApiParam(
                              value = "Specify creditCard id",
                              defaultValue = "any positive number",
                              required = true
                      ) id: Int
    ) = creditCardService.findById(id)

    @GetMapping("entities")
    fun probe(): List<CreditCardIdAndNumber> = listOf(
            CreditCardIdAndNumber(11111),
            CreditCardIdAndNumber(0),
            CreditCardIdAndNumber(-33),
            CreditCardModern(222),
            CreditCardModern(-52341),
            CreditCardModern(-52341, "Hi, there!!!")
    );

    @GetMapping("entityVals")
    fun other(): List<EntityVal> = listOf(
            EntityVal(1, "one"),
            EntityVal(2, "two"),
            EntityVal(3, "three")
    );
}
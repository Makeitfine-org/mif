/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.util.mq;

import static org.junit.Assert.assertNotEquals;

import java.security.SecureRandom;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
//import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.checker.regex.qual.Regex;
import org.junit.Test;

@Getter(onMethod_ = {@Nullable})
@Setter(onParam_ = {@Nullable})
@ToString
public class MessageTest {

    private Integer i;

    private String k = "For test success";
    private Float f = 5.5f;

    private static @Regex String regexp = "ab8abc...\\$1$1$+1+1+";
    private static @Regex(1) String FIND_NUMBERS = "(\\d*)*1*1*";

    public void setI(Integer i) {
        this.i = i;
    }

    @Test
    public void someTest() {
        assertNotEquals(new Object().toString(), someForCheckerframework().toString());
    }

    //by default all method return is nonnull (so Nunnull annotation is just mark)
    public @NonNull Object someForCheckerframework() {
        k.toCharArray();
        Optional.ofNullable(getK()).orElse("").toLowerCase();
        setK(objForCheckerframework() == null ? "no" : "yes");
        return Optional.ofNullable(getI()).orElse(125);
    }

    private @Nullable Object objForCheckerframework() {
        regexp = "ab[<][>]abc+ $1$1$|||/$^+1+1+";
        //        "abc".matches(regexp);
        System.out.println(regexp);
        return new SecureRandom().nextInt() < 0 ? null : 61;
    }

    //    private static @Regex(1) String findNumbers = "\\d*";

    public static void main(String[] args) {
        String message = "My phone number is +3911223344.";
    //        message.matches(findNumbers);
    }
}
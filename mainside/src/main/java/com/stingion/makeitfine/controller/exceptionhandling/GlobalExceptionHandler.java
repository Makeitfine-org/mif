/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller.exceptionhandling;

import java.util.Date;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Error.class, Exception.class, RuntimeException.class})
    @SuppressWarnings("checkstyle:missingjavadocmethod")
    public ModelAndView handleIOException(HttpServletRequest request, @NonNull Exception e) {
        log.error(Objects.toString(e.getMessage(), ""), e);

        ModelAndView mav = new ModelAndView("error/generic_error");
        mav.addObject("datetime", new Date());
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());

        return mav;
    }
}

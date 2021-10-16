/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Locale;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@Api(tags = "StartUpController")
public class StartUpController {

    @GetMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("info")
    @ApiOperation(
            value = "Information about project details",
            notes = "This page is allowed without authorization")
    public @ResponseBody
    String greeting(
            @RequestParam(value = "details", required = false)
            @ApiParam(
                    value = "Specify details for output",
                    defaultValue = "any value",
                    allowableValues = "author, purpose, stage")
                    String details) {
        log.info("Mainside microservice (cache-service module)");
        var detailsForSwitch = details != null ? details : "any value";
        switch (detailsForSwitch.toLowerCase(Locale.getDefault())) {
            case "author":
                return AboutProjectInfo.Author.details() + " : " + Desc.Author.description();
            case "purpose":
                return AboutProjectInfo.Purpose.details();
            case "stage":
                return AboutProjectInfo.Stage.details() + " : " + Desc.Stage.description();
            default:
                return "Make it fine \"makeitfine\"";
        }
    }

    enum AboutProjectInfo {
        Author {
            @Override
            public String details() {
                return "Stingion";
            }
        },
        Purpose {
            @Override
            public String details() {
                return "Social Network for workers";
            }
        },
        Stage {
            @Override
            public String details() {
                return "Development stage";
            }
        };

        public abstract String details();
    }

    enum Desc {
        Author(() -> "stingion@gmail.com"),
        Stage(() -> "development milestone");
        private String desc;

        Desc(Supplier<String> desc) {
            this.desc = desc.get();
        }

        public String description() {
            return desc;
        }
    }
}

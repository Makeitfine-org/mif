/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller;

import com.stingion.makeitfine.data.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("userInfo")
@Api(tags = {"UserInfoController"})
public class UserInfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("usersAndItsRoles")
    @ApiOperation("Get all users with their roles")
    public Flux<String> usersAndItsRoles() {
        return Flux.fromIterable(infoService.usersAndItsRoles());
    }
}

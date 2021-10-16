/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.service.model.UserService;
import com.stingion.makeitfine.util.UserPasswordEncoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("user")
@Api(tags = {"UserController"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired(required = false)
    private UserPasswordEncoder passwordEncoder;

    @GetMapping("userManagement")
    public ModelAndView getIndexPage() {
        return new ModelAndView("userManagement");
    }

    @GetMapping
    @ApiOperation("Get all users")
    public List<User> listAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        if (userService.findBySSO(user.getSsoId()) != null) {
            log.debug("User with ssoID {} exists", user.getSsoId());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Optional.ofNullable(passwordEncoder).ifPresent(encoder -> encoder.encodePassword(user));

        userService.save(user);
        log.info("User created {}", user);

        HttpHeaders headers = new HttpHeaders();
        headers.set("createdUserId", String.valueOf(user.getId()));
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        Function<User, ResponseEntity<User>> func = u -> {
            Optional.ofNullable(passwordEncoder).ifPresent(encoder -> encoder.encodePassword(user));

            u.setSsoId(user.getSsoId());
            u.setPassword(user.getPassword());
            u.setEmail(user.getEmail());
            u.setState(user.getState());

            userService.update(u);
            log.info("Updating User {}", u);
            return new ResponseEntity<>(u, HttpStatus.OK);
        };
        return findByIdBasedOperation(id, func);
    }

    @SuppressWarnings("checkstyle:missingjavadocmethod")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        Function<User, ResponseEntity<User>> func = u -> {
            log.info("Deleted User {}", u);
            userService.delete(u);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        };
        return findByIdBasedOperation(id, func);
    }

    private ResponseEntity<User> findByIdBasedOperation(int id, Function<User, ResponseEntity<User>> func) {
        try {
            User user = userService.findById(id);
            return func.apply(user);
        } catch (NoSuchElementException ex) {
            return notFoundResponseEntity(id);
        }
    }

    private ResponseEntity<User> notFoundResponseEntity(@PathVariable("id") long id) {
        log.debug("no element with id={}", id);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("id", String.valueOf(id));
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }
}

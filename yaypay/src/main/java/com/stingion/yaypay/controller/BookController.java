/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.controller;

import com.stingion.yaypay.data.model.tables.pojos.Book;
import com.stingion.yaypay.data.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return this.bookService.getBooks();
    }

    @PostMapping
    public void postBook(@RequestBody Book book) {
        this.bookService.insertBook(book);
    }
}

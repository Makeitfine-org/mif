/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.service;

import com.stingion.yaypay.data.model.Tables;
import com.stingion.yaypay.data.model.tables.pojos.Book;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final DSLContext context;

    public List<Book> getBooks() {
        return context
                .selectFrom(Tables.BOOK)
                .fetchInto(Book.class);
    }

    public void insertBook(Book book) {
        context
                .insertInto(Tables.BOOK, Tables.BOOK.AUTHOR,
                        Tables.BOOK.AUTHOR)
                .values(book.getTitle(), book.getAuthor())
                .execute();
    }
}

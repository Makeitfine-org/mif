/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.model;

import com.stingion.yaypay.data.model.tables.Book;
import com.stingion.yaypay.data.model.tables.records.BookRecord;
import org.jooq.Identity;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

/**
 * A class modelling foreign key relationships and constraints of tables of
 * the <code>jooq_db</code> schema.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<BookRecord, Integer> IDENTITY_BOOK = Identities0.IDENTITY_BOOK;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<BookRecord> KEY_BOOK_PRIMARY = UniqueKeys0.KEY_BOOK_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {

        public static Identity<BookRecord, Integer> IDENTITY_BOOK = Internal.createIdentity(Book.BOOK, Book.BOOK.ID);
    }

    private static class UniqueKeys0 {

        public static final UniqueKey<BookRecord> KEY_BOOK_PRIMARY = Internal
                .createUniqueKey(Book.BOOK, "KEY_book_PRIMARY", new TableField[]{Book.BOOK.ID}, true);
    }
}

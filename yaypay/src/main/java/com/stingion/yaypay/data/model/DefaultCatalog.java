/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.model;

import java.util.Arrays;
import java.util.List;
import org.jooq.Schema;
import org.jooq.impl.CatalogImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class DefaultCatalog extends CatalogImpl {

    private static final long serialVersionUID = 31323794;

    /**
     * The reference instance of <code>DEFAULT_CATALOG</code>
     */
    public static final DefaultCatalog DEFAULT_CATALOG = new DefaultCatalog();

    /**
     * The schema <code>jooq_db</code>.
     */
    public final JooqDb JOOQ_DB = JooqDb.JOOQ_DB;

    /**
     * No further instances allowed
     */
    private DefaultCatalog() {
        super("");
    }

    @Override
    public final List<Schema> getSchemas() {
        return Arrays.<Schema>asList(
                JooqDb.JOOQ_DB);
    }
}

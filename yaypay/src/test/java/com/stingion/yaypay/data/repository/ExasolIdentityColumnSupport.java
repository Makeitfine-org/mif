/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.repository;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.sql.Types;
import org.hibernate.MappingException;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class ExasolIdentityColumnSupport extends IdentityColumnSupportImpl {

    @Override
    public String appendIdentitySelectToInsert(String arg0) {
        return arg0;
    }

    @Override
    public String getIdentityColumnString(int type) throws MappingException {
        return type == Types.BIGINT
                ? "decimal(36, 0) identity not null"
                : "decimal(19, 0) identity not null";
    }

    @SuppressFBWarnings("DM_CONVERT_CASE")
    @Override
    public String getIdentitySelectString(String table, String column, int type) throws MappingException {
        return "SELECT COLUMN_IDENTITY FROM EXA_ALL_COLUMNS WHERE COLUMN_NAME='" + column.toUpperCase()
                + "' AND COLUMN_SCHEMA='"
                + table.substring(0, table.indexOf(".")).toUpperCase() + "' AND COLUMN_TABLE='"
                + (table.substring(table.indexOf(".") + 1)).toUpperCase() + "'";
    }

    @Override
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    public boolean supportsInsertSelectIdentity() {
        return false;
    }
}

package com.thc.realspr;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class TablePrefix implements PhysicalNamingStrategy {

    private final String tablePrefix = "mydb_";

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
        return name;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
        return name;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return applyPrefix(name);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
        return applyPrefix(name);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return name;
    }

    private Identifier applyPrefix(Identifier identifier) {
        if (identifier == null || identifier.getText().isEmpty()) {
            return identifier;
        }
        String newName = tablePrefix + identifier.getText();
        return Identifier.toIdentifier(newName);
    }
}

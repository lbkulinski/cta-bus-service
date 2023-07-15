/*
 * This file is generated by jOOQ.
 */
package app.cta4j.jooq.enums;


import app.cta4j.jooq.Public;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum Direction implements EnumType {

    Eastbound("Eastbound"),

    Westbound("Westbound"),

    Northbound("Northbound"),

    Southbound("Southbound");

    private final String literal;

    private Direction(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public String getName() {
        return "direction";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal
     */
    public static Direction lookupLiteral(String literal) {
        return EnumType.lookupLiteral(Direction.class, literal);
    }
}

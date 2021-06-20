package com.airton.psjava.enums;

public enum EnumShopcartSortAttribute {

    PRICE("PRICE"),
    SCORE("SCORE"),
    NAME("NAME");

    private String sortAttribute;

    EnumShopcartSortAttribute(String sortAttribute) {
        this.sortAttribute = sortAttribute;
    }

    public static EnumShopcartSortAttribute valueOfString(String sortAttribute) {
        for (EnumShopcartSortAttribute item : EnumShopcartSortAttribute.values()) {
            if (item.getSortAttribute().equals(sortAttribute)) {
                return item;
            }
        }
        return null;
    }

    public String getSortAttribute() {
        return sortAttribute;
    }

}

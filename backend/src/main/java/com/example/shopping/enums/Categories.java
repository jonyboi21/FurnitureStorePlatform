package com.example.shopping.enums;


public enum Categories {
    TEXTBOOK("TEXTBOOK"),
    FICTION("FICTION"),
    NOVEL("NOVEL"),
    NONFICTION("NON-FICTION");

    private final String categories;

    Categories(String categories) {
        this.categories = categories;
    }

    public String getCategories() {
        return categories;
    }
}

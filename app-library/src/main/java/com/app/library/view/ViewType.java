package com.app.library.view;

public enum ViewType {
    MAIN {
        public String getFxmlName() { return "shared/main"; }
    },
    READER_SEARCH_BOOKS {
        public String getFxmlName() { return "reader/search-books"; }
    };

    public abstract String getFxmlName();
}

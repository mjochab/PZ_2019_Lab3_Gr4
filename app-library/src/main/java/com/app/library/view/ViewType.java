package com.app.library.view;

public enum ViewType {
    MAIN {
        public String getFxmlName() { return "main"; }
    },
    SECOND {
        public String getFxmlName() { return "second"; }
    };

    public abstract String getFxmlName();
}

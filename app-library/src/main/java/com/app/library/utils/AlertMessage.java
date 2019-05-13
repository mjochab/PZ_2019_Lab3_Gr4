package com.app.library.utils;

import java.util.Objects;

public class AlertMessage {
    private AlertMessage() {}

    private String content;
    private String title;
    private String header;

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getHeader() {
        return header;
    }

    public static class Builder {

        private String content;
        private String title;
        private String header = "Błąd";

        public Builder content(String content) {
            this.content = Objects.requireNonNull(content);
            return this;
        }

        public Builder title(String title) {
            this.title = Objects.requireNonNull(title);
            return this;
        }

        public Builder header(String header) {
            this.header = Objects.requireNonNull(header);
            return this;
        }

        public AlertMessage build() {
            return new AlertMessage(this.content, this.title, this.header);
        }
    }

    private AlertMessage(String content, String title, String header) {
        this.content = Objects.requireNonNull(content);
        this.title = title;
        this.header = Objects.requireNonNull(header);
    }
}

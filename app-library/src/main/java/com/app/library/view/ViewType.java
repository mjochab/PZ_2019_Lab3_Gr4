package com.app.library.view;

public enum ViewType {
    MAIN {
        public String getFxmlName() {
            return "shared/glowna";
        }

        public String getFrameTitle() {
            return "Biblioteka";
        }
    },
    READER_SEARCH_BOOKS {
        public String getFxmlName() {
            return "reader/search-books";
        }

        public String getFrameTitle() {
            return "Wyszukiwarka książek";
        }
    },
    READER_ACCOUNT {
        public String getFxmlName() {
            return "reader/account";
        }

        public String getFrameTitle() {
            return "Konto";
        }
    },
    EMPLOYER_ACCOUNT {
        public String getFxmlName() {
            return "employer/account-employer";
        }

        public String getFrameTitle() {
            return "Konto";
        }
    },
    REGISTER {
        public String getFxmlName() {
            return "shared/rejestracja";
        }

        public String getFrameTitle() {
            return "Rejestracja";
        }
    },
    SIGN_IN {
        public String getFxmlName() {
            return "shared/logowanie";
        }

        public String getFrameTitle() {
            return "Logowanie";
        }
    };

    public abstract String getFxmlName();

    public abstract String getFrameTitle();
}
package com.app.library.view;

public enum ViewType {
    // reader
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
    READER_MY_ORDERS {
        public String getFxmlName() {
            return "reader/my_orders";
        }

        public String getFrameTitle() {
            return "Moje zamówienia";
        }
    },

    // shared
    SIGN_IN {
        public String getFxmlName() {
            return "shared/logowanie";
        }

        public String getFrameTitle() {
            return "Logowanie";
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
    MAIN {
        public String getFxmlName() {
            return "shared/glowna";
        }

        public String getFrameTitle() {
            return "Biblioteka";
        }
    },

    // employee
    EMPLOYEE_LIST_OF_BOOKS {
        public String getFxmlName() {
            return "employee/books-list";
        }

        public String getFrameTitle() {
            return "Lista książek";
        }
    },
    EMPLOYEE_REALIZED_ORDERS {
        public String getFxmlName() {
            return "employee/realized-orders";
        }

        public String getFrameTitle() {
            return "Zamówienia";
        }
    },
    EMPLOYEE_ACCOUNT {
        public String getFxmlName() {
            return "employee/account-employee";
        }

        public String getFrameTitle() {
            return "Konto";
        }
    },
    EMPLOYEE_LIST_OF_USERS {
        public String getFxmlName() {
            return "employee/users-list";
        }

        public String getFrameTitle() {
            return "Użytkownicy";
        }
    },
    EMPLOYEE_ADD_OF_BOOKS {
        public String getFxmlName() {

            return "employer/adding-book";
        }

        public String getFrameTitle() {
            return "Dodawanie książki";
        }
    },
    EMPLOYER_BACK {
        public String getFxmlName() {

            return "employer/account-employer";
        }

        public String getFrameTitle() {
            return "Konto";
        }
    };

    public abstract String getFxmlName();

    public abstract String getFrameTitle();
}
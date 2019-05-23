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
    READER_CART {
        public String getFxmlName(){
            return "reader/cart";
        }

        public String getFrameTitle(){
            return "Koszyk";
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
    EMPLOYEE_READER_ORDERS {
        public String getFxmlName() {
            return "employee/reader-orders";
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

            return "employee/adding-book";
        }

        public String getFrameTitle() {
            return "Dodawanie książki";
        }
    },

    // boss
    BOSS_ACCOUNT {
        public String getFxmlName() {
            return "boss/account-boss";
        }

        public String getFrameTitle() {
            return "Konto";
        }
    },
    BOSS_LIST_OF_EMPLOYEE {
        public String getFxmlName() { return "boss/employee-list"; }

        public String getFrameTitle() { return "Lista pracowników"; }
    },
    BOSS_LIST_OF_LIBRERIES {
        public String getFxmlName() { return "boss/libraries-list"; }

        public String getFrameTitle() { return "Lista bibliotek"; }
    },
    BOSS_ADD_EMPLOYEE {
        public String getFxmlName() { return "boss/add-employee"; }

        public String getFrameTitle() { return "Dodaj prcownika"; }
    },

    // shared
    SINGLE_BOOK {
      public String getFxmlName() {return "shared/individual-book's-view";}

      public String getFrameTitle() { return "shared/individual-book's-view"; }
    },
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
    SINGLE_ORDER {

        public String getFxmlName() {
            return "reader/individual-order-view";
        }

        public String getFrameTitle() {
            return "Order";
        }
    },
    EMPLOYEE_SINGLE_ORDER {
        public String getFxmlName() {return "employee/single-order";}

        public String getFrameTitle() { return "Order";}
    },
    EMPLOYEE_EDITING_BOOK {
        public String getFxmlName() {return "employee/editing-book";}

        public String getFrameTitle() { return "Edit";}
    };

    public abstract String getFxmlName();

    public abstract String getFrameTitle();
}
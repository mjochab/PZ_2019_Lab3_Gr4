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
    },
    READER_MY_ORDERS {
      public String getFxmlName(){
          return "reader/my_orders";
      }

      public String getFrameTitle(){
          return "Moje zamówienia";
      }
    },
    EMPLOYER_LIST_OF_BOOKS {
        public String getFxmlName(){
            return "employer/books-list";
        }

        public String getFrameTitle(){
            return "Lista książek";
        }
    },
    EMPLOYER_REALIZED_ORDERS {
        public String getFxmlName(){
            return "employer/realized-orders";
        }

        public String getFrameTitle(){
            return "Zamówienia";
        }
    },
    EMPLOYER_LIST_OF_USERS {
        public String getFxmlName(){
            return "employer/users-list";
        }

        public String getFrameTitle(){
            return "Użytkownicy";
        }
    };

    public abstract String getFxmlName();

    public abstract String getFrameTitle();
}
package com.app.library.service;

import com.app.library.model.BookUnit;
import com.app.library.model.BooksOrder;
import com.app.library.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Service
public class PersistenceService {

    private User user;

    private User employee;

    private BooksOrder selectedBooksOrder;

    private HashMap<String, Object> storedObjects;

    private List<BookUnit> cart = new ArrayList<>();

    public PersistenceService() {
        this.storedObjects = new HashMap<>();
    }

    public void addToStore(String key, Object object) {
        this.storedObjects.put(requireNonNull(key), requireNonNull(object));
    }

    public Object getStoredObject(String key) {
        if (this.storedObjects.containsKey(key)) {
            return this.storedObjects.get(key);
        }
        throw new RuntimeException(String.format("Object with key %s not exist", key));
    }

    public void cleanCart(){
        cart.clear();
    }

    public void addToCart(BookUnit bookUnit) {
        this.cart.add(bookUnit);
    }

    public List<BookUnit> getCart() {
        return this.cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BooksOrder getSelectedBooksOrder() {
        return selectedBooksOrder;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public void setSelectedBooksOrder(BooksOrder selectedBooksOrder) {
        this.selectedBooksOrder = selectedBooksOrder;
    }
}

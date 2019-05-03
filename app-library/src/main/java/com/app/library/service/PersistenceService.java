package com.app.library.service;

import com.app.library.model.BookUnit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Service
public class PersistenceService {

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


    public void addToCart(BookUnit bookUnit) {
        this.cart.add(bookUnit);
    }

    public List<BookUnit> getCart() {
        return this.cart;
    }
}

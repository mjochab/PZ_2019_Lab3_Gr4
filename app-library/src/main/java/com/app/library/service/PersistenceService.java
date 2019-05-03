package com.app.library.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class PersistenceService {

    private HashMap<String, Object> storedObjects;

    public PersistenceService() {
        this.storedObjects = new HashMap<>();
    }

    public void addToStore(String key, Object object) {
        this.storedObjects.put(Objects.requireNonNull(key), Objects.requireNonNull(object));
    }

    public Object getStoredObject(String key) {
        if (this.storedObjects.containsKey(key)) {
            return this.storedObjects.get(key);
        }
        throw new RuntimeException(String.format("Object with key %s not exist", key));
    }
}

package com.app.library.service;

import com.app.library.model.Library;
import com.app.library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public List<Library> findAll() {
        return libraryRepository.findAll();
    }
}

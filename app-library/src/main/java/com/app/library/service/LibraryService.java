package com.app.library.service;

import com.app.library.model.Library;
import com.app.library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private LibraryRepository libraryRepository;

    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    public Library save(Library library) { return this.libraryRepository.save(library); }

    @Autowired
    public void setLibraryRepository(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }
}

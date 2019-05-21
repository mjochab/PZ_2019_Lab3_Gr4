package com.app.library.service;
import com.app.library.model.EmployeeOfLibrary;
import com.app.library.repository.EmployeeOfLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeOfLibraryService {
    private EmployeeOfLibraryRepository employeeOfLibraryRepository;

    public List<EmployeeOfLibrary> findAll() {
        return employeeOfLibraryRepository.findAll();
    }

    public EmployeeOfLibrary save(EmployeeOfLibrary employee) { return this.employeeOfLibraryRepository.save(employee); }

    @Autowired
    public void setEmployeeOfLibraryRepository(EmployeeOfLibraryRepository employeeOfLibraryRepository) {
        this.employeeOfLibraryRepository = employeeOfLibraryRepository;
    }
}

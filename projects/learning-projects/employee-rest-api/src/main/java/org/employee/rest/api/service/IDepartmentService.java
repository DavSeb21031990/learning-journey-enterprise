package org.employee.rest.api.service;

import org.employee.rest.api.model.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    List<Department> findAll();

    Optional<Department> findById(String id);

    Optional<Department> findByName(String name);
}

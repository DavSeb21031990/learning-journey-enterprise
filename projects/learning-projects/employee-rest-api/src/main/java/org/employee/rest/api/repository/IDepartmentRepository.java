package org.employee.rest.api.repository;

import org.employee.rest.api.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, String> {

    Optional<Department> findByName(String name);

}

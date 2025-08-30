package org.employee.rest.api.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.employee.rest.api.model.Department;
import org.employee.rest.api.repository.IDepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final IDepartmentRepository repository;

    @PostConstruct
    public void init(){

        if(this.repository.count() > 0){
            System.out.println("📊 Database already contains " + repository.count() + " departments");
            return;
        }

        List<Department> departments = List.of(
          Department.builder().id("DEPT001").name("Engineering").build(),
          Department.builder().id("DEPT002").name("Marketing").build(),
          Department.builder().id("DEPT003").name("Sales").build(),
          Department.builder().id("DEPT004").name("HR").build()
        );

        this.repository.saveAll(departments);
        System.out.println("✅ Loaded " + departments.size() + " departments to database");

    }

    @Override
    public List<Department> findAll(){
        return this.repository.findAll();
    }

    @Override
    public Optional<Department> findById(String id){
        return this.repository.findById(id);
    }

    @Override
    public Optional<Department> findByName(String name){
        return this.repository.findByName(name);
    }
}

package org.employee.rest.api.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.employee.rest.api.dto.analytics.DepartmentCountDto;
import org.employee.rest.api.dto.analytics.DepartmentSalaryDto;
import org.employee.rest.api.dto.request.EmployeeCreateRequest;
import org.employee.rest.api.model.Department;
import org.employee.rest.api.model.Employee;
import org.employee.rest.api.repository.IEmployeeRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@DependsOn("departmentService")
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final IDepartmentService departmentService;

    private final IEmployeeRepository repository;

    private final AtomicInteger counter = new AtomicInteger(9);

    @PostConstruct
    public void init() {
        if (repository.count() > 0) {
            System.out.println("📊 Database already contains " + repository.count() + " employees");
            return;
        }

        Department deptEngineering = this.departmentService.findById("DEPT001").orElseThrow(() -> new IllegalArgumentException("Department not found"));
        Department deptMarketing = this.departmentService.findById("DEPT002").orElseThrow(() -> new IllegalArgumentException("Department not found"));
        Department deptSales = this.departmentService.findById("DEPT003").orElseThrow(() -> new IllegalArgumentException("Department not found"));

        List<Employee> employeeLst = List.of(
                Employee.builder().id("EMP001").name("Juan Perez").salary(75000.0).experience(5).level("Mid").department(deptEngineering).build(),
                Employee.builder().id("EMP002").name("Maria Lopez").salary(65000.0).experience(3).level("Junior").department(deptMarketing).build(),
                Employee.builder().id("EMP003").name("Pedro Garcia").salary(95000.0).experience(8).level("Senior").department(deptEngineering).build(),
                Employee.builder().id("EMP004").name("Ana Rodriguez").salary(55000.0).experience(2).level("Junior").department(deptSales).build(),
                Employee.builder().id("EMP005").name("Carlos Mendez").salary(105000.0).experience(12).level("Senior").department(deptEngineering).build(),
                Employee.builder().id("EMP006").name("Sofia Chen").salary(75000.0).experience(6).level("Mid").department(deptMarketing).build(),
                Employee.builder().id("EMP007").name("Luis Ramirez").salary(48000.0).experience(1).level("Junior").department(deptSales).build(),
                Employee.builder().id("EMP008").name("Isabella Torres").salary(82000.0).experience(4).level("Mid").department(deptEngineering).build()
        );

        repository.saveAll(employeeLst);
        System.out.println("✅ Loaded " + employeeLst.size() + " employees to database");

    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Employee> findById(String id) {
        return this.repository.findById(id);
    }

    @Transactional
    @Override
    public Employee save(EmployeeCreateRequest request) {

        Employee employee = dtoCreateRequestToEmployee(request);
        employee.setId(generatorId());

        return this.repository.save(employee);
    }

    private String generatorId() {
        return String.format("EMP%03d", counter.getAndIncrement());
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Long> getDepartmentCounts() {
        return this.repository.getDepartmentCounts()
                .stream()
                .collect(Collectors.toMap(DepartmentCountDto::getDepartmentName, DepartmentCountDto::getEmployeeCount));
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Double> getAverageSalaryByDepartment() {
        return this.repository.getAverageSalaryByDepartment()
                .stream()
                .collect(Collectors.toMap(DepartmentSalaryDto::getDepartmentName, DepartmentSalaryDto::getAverageSalary));
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Boolean, List<Employee>> getPerformanceSegmentation(double threshold) {
        return this.repository.findByHighPerformers(threshold)
                .stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() > threshold));
    }

    private Employee dtoCreateRequestToEmployee(EmployeeCreateRequest request) {

        Department department = this.departmentService.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        
        return Employee.builder()
                .name(request.getName())
                .salary(request.getSalary())
                .experience(request.getExperience())
                .level(request.getLevel())
                .department(department)
                .build();
    }
}

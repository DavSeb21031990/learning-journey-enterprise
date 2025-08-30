package org.employee.rest.api.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.employee.rest.api.dto.EmployeeCreateRequest;
import org.employee.rest.api.model.Employee;
import org.employee.rest.api.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository repository;

    private final AtomicInteger counter = new AtomicInteger(0);

    @PostConstruct
    public void init() {
        if (repository.count() > 0) {
            System.out.println("📊 Database already contains " + repository.count() + " employees");
            return;
        }
        List<Employee> employeeLst = List.of(
                new Employee("EMP001", "Juan Perez", "Engineering", 75000.0, 5, "Mid"),
                new Employee("EMP002", "Maria Lopez", "Marketing", 65000.0, 3, "Junior"),
                new Employee("EMP003", "Pedro Garcia", "Engineering", 95000.0, 8, "Senior"),
                new Employee("EMP004", "Ana Rodriguez", "Sales", 55000.0, 2, "Junior"),
                new Employee("EMP005", "Carlos Mendez", "Engineering", 105000.0, 12, "Senior"),
                new Employee("EMP006", "Sofia Chen", "Marketing", 75000.0, 6, "Mid"),
                new Employee("EMP007", "Luis Ramirez", "Sales", 48000.0, 1, "Junior"),
                new Employee("EMP008", "Isabella Torres", "Engineering", 82000.0, 4, "Mid")
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
        return String.format("%03d", counter.getAndIncrement());
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Long> getDepartmentCounts() {
        return this.repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Double> getAverageSalaryByDepartment() {
        return this.repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Boolean, List<Employee>> getPerformanceSegmentation(double threshold) {
        return this.repository.findAll()
                .stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() > threshold));
    }

    private Employee dtoCreateRequestToEmployee(EmployeeCreateRequest request) {
        return Employee.builder()
                .name(request.getName())
                .department(request.getDepartment())
                .salary(request.getSalary())
                .experience(request.getExperience())
                .level(request.getLevel())
                .build();
    }
}

package org.employee.rest.api.service;

import jakarta.annotation.PostConstruct;
import org.employee.rest.api.dto.EmployeeCreateRequest;
import org.employee.rest.api.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService{

    private final AtomicInteger counter = new AtomicInteger(8);
    private final Map<String, Employee> employees = new HashMap<>();

    @PostConstruct
    public void init() {
        employees.put("EMP001", new Employee("EMP001", "Juan Perez", "Engineering", 75000, 5, "Mid"));
        employees.put("EMP002", new Employee("EMP002", "Maria Lopez", "Marketing", 65000, 3, "Junior"));
        employees.put("EMP003", new Employee("EMP003", "Pedro Garcia", "Engineering", 95000, 8, "Senior"));
        employees.put("EMP004", new Employee("EMP004", "Ana Rodriguez", "Sales", 55000, 2, "Junior"));
        employees.put("EMP005", new Employee("EMP005", "Carlos Mendez", "Engineering", 105000, 12, "Senior"));
        employees.put("EMP006", new Employee("EMP006", "Sofia Chen", "Marketing", 75000, 6, "Mid"));
        employees.put("EMP007", new Employee("EMP007", "Luis Ramirez", "Sales", 48000, 1, "Junior"));
        employees.put("EMP008", new Employee("EMP008", "Isabella Torres", "Engineering", 82000, 4, "Mid"));
    }

    @Override
    public List<Employee> findAll() {
        return employees.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(employees.get(id));
    }

    @Override
    public Employee save(EmployeeCreateRequest request) {

        String id = "EMP" + String.format("%03d", counter.getAndIncrement());
        Employee employee = dtoCreateRequestToEmployee(request);
        employee.setId(id);
        this.employees.put(id, employee);

        return employee;
    }

    @Override
    public Map<String, Long> getDepartmentCounts() {
        return employees.values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    @Override
    public Map<String, Double> getAverageSalaryByDepartment() {
        return employees.values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    }

    @Override
    public Map<Boolean, List<Employee>> getPerformanceSegmentation(double threshold) {
        return employees.values()
                .stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() > threshold));
    }

    private Employee dtoCreateRequestToEmployee(EmployeeCreateRequest request){
        return Employee.builder()
                .name(request.getName())
                .department(request.getDepartment())
                .salary(request.getSalary())
                .experience(request.getExperience())
                .level(request.getLevel())
                .build();
    }
}

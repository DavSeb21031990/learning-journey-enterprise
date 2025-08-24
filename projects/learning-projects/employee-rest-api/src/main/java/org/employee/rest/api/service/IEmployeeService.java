package org.employee.rest.api.service;

import org.employee.rest.api.dto.EmployeeCreateRequest;
import org.employee.rest.api.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IEmployeeService {

    List<Employee> findAll();

    Optional<Employee> findById(String id);

    Employee save(EmployeeCreateRequest request);

    Map<String, Long> getDepartmentCounts();

    Map<String, Double> getAverageSalaryByDepartment();

    Map<Boolean, List<Employee>> getPerformanceSegmentation(double threshold);

}

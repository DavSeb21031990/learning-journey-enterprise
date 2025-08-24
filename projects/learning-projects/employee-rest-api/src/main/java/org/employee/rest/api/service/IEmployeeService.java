package org.employee.rest.api.service;

import org.employee.rest.api.dto.EmployeeCreateRequest;
import org.employee.rest.api.dto.EmployeeResponse;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {

    List<EmployeeResponse> findAll();

    EmployeeResponse findById(String id);

    EmployeeResponse save(EmployeeCreateRequest request);

    Map<String, Long> getDepartmentCounts();

    Map<String, Double> getAverageSalaryByDepartment();

    Map<String, List<EmployeeResponse>> getPerformanceSegmentation(double threshold);

}

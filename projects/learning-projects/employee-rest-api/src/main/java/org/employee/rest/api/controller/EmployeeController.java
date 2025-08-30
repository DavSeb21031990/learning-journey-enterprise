package org.employee.rest.api.controller;

import jakarta.validation.Valid;
import org.employee.rest.api.dto.request.EmployeeCreateRequest;
import org.employee.rest.api.dto.response.EmployeeResponse;
import org.employee.rest.api.model.Employee;
import org.employee.rest.api.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees(){
        return ResponseEntity.ok(employeeService.findAll().stream().map(this::modelToDtoResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable String id){
        return employeeService.findById(id)
                .map(this::modelToDtoResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public  ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeCreateRequest request){

        Employee employee = employeeService.save(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        return  ResponseEntity.created(location)
                .body(this.modelToDtoResponse(employee));
    }

    @GetMapping("/analytics/department-counts")
    public ResponseEntity<Map<String, Long>> getDepartmentCounts(){
        return ResponseEntity.ok(employeeService.getDepartmentCounts());
    }

    @GetMapping("/analytics/average-salary-by-department")
    public  ResponseEntity<Map<String, Double>> getAverageSalaryByDepartment(){
        return ResponseEntity.ok(employeeService.getAverageSalaryByDepartment());
    }

    @GetMapping("/analytics/performance-segmentation")
    public  ResponseEntity<Map<String, List<EmployeeResponse>>> getPerformanceSegmentation(
            @RequestParam(defaultValue = "70000") double threshold){
        Map<Boolean, List<Employee>> performanceSegmentation = employeeService.getPerformanceSegmentation(threshold);
        Map<String, List<EmployeeResponse>> response = new HashMap<>();

        response.put("highPerformance", performanceSegmentation.get(true)
                .stream()
                .map(this::modelToDtoResponse)
                .toList());

        response.put("lowPerformance", performanceSegmentation.get(false)
                .stream()
                .map(this::modelToDtoResponse)
                .toList());

        return ResponseEntity.ok(response);
    }

    private EmployeeResponse modelToDtoResponse(Employee model){
        return EmployeeResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .salary(model.getSalary())
                .experience(model.getExperience())
                .level(model.getLevel())
                .build();
    }

}

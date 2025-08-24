package org.employee.rest.api.controller;

import jakarta.validation.Valid;
import org.employee.rest.api.dto.EmployeeCreateRequest;
import org.employee.rest.api.dto.EmployeeResponse;
import org.employee.rest.api.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable String id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    public  ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeCreateRequest request){

        EmployeeResponse employee = employeeService.save(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        return  ResponseEntity.created(location)
                .body(employee);
    }

    @GetMapping("/analytics/department-counts")
    public ResponseEntity<Map<String, Long>> getDepartmentCounts(){
        return ResponseEntity.ok(employeeService.getDepartmentCounts());
    }

    @GetMapping("/analytics/average-salary-by-department")
    public  ResponseEntity<Map<String, Double>> getAverageSalaryByDepartment(){
        return ResponseEntity.ok(employeeService.getAverageSalaryByDepartment());
    }

    @GetMapping("/analytics/perfomance-segmentation")
    public  ResponseEntity<Map<String, List<EmployeeResponse>>> getPerformanceSegmentation(
            @RequestParam(defaultValue = "70000") double threshold){
        return ResponseEntity.ok(employeeService.getPerformanceSegmentation(threshold));
    }

}

package com.example;

import com.example.entities.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDashboard {

    public static void main(String[] args) {

        // Extended Employee dataset
        List<Employee> employees = Arrays.asList(
                new Employee("EMP001", "Juan Perez", "Engineering", 75000, 5, "Mid"),
                new Employee("EMP002", "Maria Lopez", "Marketing", 65000, 3, "Junior"),
                new Employee("EMP003", "Pedro Garcia", "Engineering", 95000, 8, "Senior"),
                new Employee("EMP004", "Ana Rodriguez", "Sales", 55000, 2, "Junior"),
                new Employee("EMP005", "Carlos Mendez", "Engineering", 105000, 12, "Senior"),
                new Employee("EMP006", "Sofia Chen", "Marketing", 75000, 6, "Mid"),
                new Employee("EMP007", "Luis Ramirez", "Sales", 48000, 1, "Junior"),
                new Employee("EMP008", "Isabella Torres", "Engineering", 82000, 4, "Mid")
        );

        System.out.println("=== EMPLOYEE ANALYTICS DASHBOARD ===\n");

        // TODO 1: Group employees by department
        System.out.println("1. EMPLOYEES BY DEPARTMENT:");
        Map<String, List<Employee>> employeeByDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        employeeByDepartment.entrySet().forEach(System.out::println);
        System.out.println();

        // TODO 2: Count employees per department
        System.out.println("\n2. DEPARTMENT SIZES:");
        Map<String, Long> nroEmployeeByDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        nroEmployeeByDepartment.entrySet().forEach(System.out::println);
        System.out.println();

        // TODO 3: Average salary by department
        System.out.println("\n3. AVERAGE SALARY BY DEPARTMENT:");
        Map<String, Double> salaryAverageByDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

        salaryAverageByDepartment.entrySet().forEach(System.out::println);
        System.out.println();

        // TODO 4: Partition by high/low performers (>$70k)
        System.out.println("\n4. PERFORMANCE SEGMENTATION:");
        Map<Boolean, List<Employee>> segmentationPerformance = employees
                .stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() > 70000));

        segmentationPerformance.get(true).forEach(System.out::println);
        System.out.println();
        segmentationPerformance.get(false).forEach(System.out::println);
        System.out.println();

        // TODO 5: Complete salary stats by department
        System.out.println("\n5. SALARY STATISTICS BY DEPARTMENT:");
        Map<String, DoubleSummaryStatistics> salarySummarizingByDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summarizingDouble(Employee::getSalary)));

        System.out.println("\n=== ADVANCED ANALYTICS ===\n");

        // TODO 6: Multi-level grouping - Department then Level
        System.out.println("6. EMPLOYEES BY DEPARTMENT AND LEVEL:");

        Map<String, Map<String, List<Employee>>> employeeByDepartmentAndLevel = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.groupingBy(Employee::getLevel)));


        // TODO 7: Partition each department by experience (>5 years)
        System.out.println("\n7. EXPERIENCE SEGMENTATION BY DEPARTMENT:");

        Map<String, Map<Boolean, List<Employee>>> partitionDepartmentByExperienceGreaterThan5 = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.partitioningBy(emp -> emp.getExperience() > 5)));
        
        // TODO 8: Top department by average salary
        System.out.println("\n8. HIGHEST PAYING DEPARTMENT:");

        Map<String, Double> salaryAverageByDepartmentOpt = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

        Optional<Map.Entry<String, Double>> departmentWithHighestAverageSalary = salaryAverageByDepartmentOpt.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        if(departmentWithHighestAverageSalary.isPresent()) {
            System.out.println("Department: " +  departmentWithHighestAverageSalary.get().getKey() +
                    " Average Salary: " + departmentWithHighestAverageSalary.get().getValue());
        }else{
            System.out.println("No hay datos para realizar la consulta.");
        }

        // TODO 9: Senior employees by department count
        System.out.println("\n9. SENIOR EMPLOYEES COUNT BY DEPARTMENT:");

        Map<String, Long> seniorEmployeeCountByDepartment = employees
                .stream()
                .filter(emp -> emp.getLevel().equals("Senior"))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));


        // TODO 10: Complex business query - Your choice!
        System.out.println("\n10. CUSTOM BUSINESS INSIGHT:");

        System.out.println("\n=== EXECUTIVE DASHBOARD SUMMARY ===");

        // TODO: Create a comprehensive summary combining multiple analytics
        // Example: "Engineering has X employees, avg salary $Y, Z% are senior level"

        Map<String, List<Employee>> junior = employees
                .stream()
                .filter(emp -> emp.getLevel().equals("Junior"))
                .filter(emp -> emp.getExperience() > 3)
                .sorted(Comparator.comparing(Employee::getDepartment).thenComparing(Employee::getSalary).reversed())
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}

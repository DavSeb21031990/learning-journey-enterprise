package com.example;

import com.example.entities.Employee;

import java.util.*;
import java.util.stream.Collectors;


public class EmployeeStreams {

    public static void main(String[] args) {

        // Setup test data
        List<Employee> employees = Arrays.asList(
                new Employee("EMP001", "Juan Perez", "Engineering", 75000, 5),
                new Employee("EMP002", "Maria Lopez", "Marketing", 65000, 3),
                new Employee("EMP003", "Pedro Garcia", "Engineering", 85000, 7),
                new Employee("EMP004", "Ana Rodriguez", "Sales", 55000, 2),
                new Employee("EMP005", "Carlos Mendez", "Engineering", 95000, 10)
        );

        System.out.println("=== Employee Streams Practice ===");

        // TODO 1: Filter engineers only
        List<Employee> engineeringLst = employees
                .stream()
                .filter(emp -> emp.getDepartment().equals("Engineering"))
                .toList();

        engineeringLst.forEach(System.out::println);
        System.out.println();

        // TODO 2: Get names of all employees
        List<String> nameEmployeeLst = employees
                .stream()
                .map(Employee::getName)
                .toList();

        nameEmployeeLst.forEach(System.out::println);
        System.out.println();

        // TODO 3: Find employees with salary > 70k
        List<Employee> employeeSalaryGreaterThan70000Lst = employees
                .stream()
                .filter(emp -> emp.getSalary() > 70000)
                .toList();

        employeeSalaryGreaterThan70000Lst.forEach(System.out::println);
        System.out.println();

        // TODO 4: Get names of engineers with >5 years experience
        List<Employee> engineeringMore5ExperienceYearLst = employees
                .stream()
                .filter(emp -> emp.getDepartment().equals("Engineering"))
                .filter(emp -> emp.getExperience() > 5)
                .toList();

        engineeringMore5ExperienceYearLst.forEach(System.out::println);
        System.out.println();

        // TODO 5: Count employees by department
        Map<String, Long> nroEmployeeByDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        nroEmployeeByDepartment.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n=== Advanced Streams Challenges ===");

        // TODO 6: Average salary of engineers
        OptionalDouble averageSalary = employees
                .stream()
                .filter(emp -> emp.getDepartment().equals("Engineering"))
                .mapToDouble(Employee::getSalary)
                .average();

        // TODO 7: Group employees by department
        Map<String, List<Employee>> employeeByDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        // TODO 8: Find highest paid employee
        Optional<Employee> employeeHighestPaid = employees
                .stream()
                .max(Comparator.comparing(Employee::getSalary));

        // TODO 9: Get departments with >1 employee
        Map<String, Long> employeeLst = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        List<String> departmentLst = employeeLst.entrySet()
                .stream()
                .filter(dep -> dep.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();

        // TODO 10: Pipeline complex: Names of engineers earning >average salary

        OptionalDouble salaryAverage = employees
                .stream()
                .filter(emp -> emp.getDepartment().equals("Engineering"))
                .mapToDouble(Employee::getSalary)
                .average();

        if(salaryAverage.isPresent()){
            List<String> engineeringSalaryGreaterAverageSalaryLst = employees
                    .stream()
                    .filter(emp -> emp.getDepartment().equals("Engineering"))
                    .filter(emp -> emp.getSalary() > salaryAverage.getAsDouble())
                    .map(Employee::getName)
                    .toList();
        }else{
            System.out.println("No exite empleados del departamente de ingerieria con un salario mayor al promedio");
        }

        System.out.println("\n=== Streams vs Loops Comparison ===");

        // TODO: Implement same query both ways and compare readability
        // Traditional Loop Way:
        List<Employee> employeeLoopLst = new ArrayList<>();
        for (Employee emp: employees){
            if(emp.getDepartment().equals("Engineering")){
                employeeLoopLst.add(emp);
            }
        }

        // Streams Way:
        List<Employee> engineeringStreamLst = employees
                .stream()
                .filter(emp -> emp.getDepartment().equals("Engineering"))
                .toList();

    }

}

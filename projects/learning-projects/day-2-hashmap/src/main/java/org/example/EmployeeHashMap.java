package org.example;

import java.util.*;

class Employee{
    private String id;
    private String name;
    private String department;

    Employee(String id, String name, String department){
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

public class EmployeeHashMap {

    public static void main(String[] args) {

        System.out.println("=== Employee Management with HashMap ===");

        // TODO: 1. Crear HashMap<String, Employee>
        Map<String, Employee> employeeMap = new HashMap<>();

        // TODO: 2. Agregar 3 employees con IDs como keys
        // EMP001 -> Juan Perez, Engineering
        // EMP002 -> Maria Lopez, Marketing
        // EMP003 -> Pedro Garcia, Sales

        Employee emp1 = new Employee("EMP001", "Juan", "Engineering");
        Employee emp2 = new Employee("EMP002", "Juan", "Engineering");
        Employee emp3 = new Employee("EMP003", "Pedro Garcia", "Sales");

        employeeMap.put("EMP001", emp1);
        employeeMap.put("EMP002", emp2);
        employeeMap.put("EMP003", emp3);

        // TODO: 3. Buscar employee por ID (demostrar O(1))
        // Buscar "EMP001" y mostrar resultado
        Employee emp001 = employeeMap.get("EMP001");
        if(employeeMap.containsKey("EMP001")){
            System.out.println("Se encontro al empleado: EMP001 - " +  employeeMap.get("EMP001").toString());
        }else{
            System.out.println("No se encontró al empleado: EMP001");
        }


        // TODO: 4. Comparar con ArrayList approach
        // Comentario: ¿Por qué HashMap es mejor aquí?
        //Map usa la función hash para acceder de forma isntantae al valor del empleado
        Employee emp002 = employeeMap.get("EMP002");
        System.out.println("Empleado encontradó - Map 0(1) " + emp002.toString());

        List<Employee> employeeList = Arrays.asList(emp1, emp2, emp3);
        for (Employee employee : employeeList) {
            if(employee.getId().equals("EMP002")){
                System.out.println("Empleado encontradó - ArrayList 0(n) " + employee.toString());
                break;
            }
        }

    }

}

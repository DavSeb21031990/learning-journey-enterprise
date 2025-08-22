package org.example;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasics {

    public static void main(String[] args) {

        System.out.println("=== HashMap Basics Practice ===");

        // 1. Crear HashMap básico
        Map<String, Integer> employees = new HashMap<>();

        // 2. Agregar 3 empleados (nombre -> edad)
        employees.put("Juan", 25);
        employees.put("Maria", 30);
        employees.put("Pedro", 28);

        // 3. Buscar un empleado específico
        Integer employeeSearch = employees.get("Juan");
        System.out.println("El empleado Juan tiene " + employeeSearch + " años.");

        // 4. Verificar si existe un empleado
        boolean exist = employees.containsKey("Pedro");
        System.out.println("El empleado Pedro " + (exist?"Si existe" : "No existe"));

        // 5. Mostrar el tamaño
        int size = employees.size();
        System.out.println("Tamaño del Map es " + size);


        // 6. Iterar todos los empleados
        employees.forEach((key, values) -> {
            System.out.println("Key: " + key + " - Values: " + values);
        });

    }

}

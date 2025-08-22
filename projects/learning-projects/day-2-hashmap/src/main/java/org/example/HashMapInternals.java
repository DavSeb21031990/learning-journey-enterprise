package org.example;

import java.util.HashMap;
import java.util.Map;

public class HashMapInternals {

    public static void main(String[] args) {
        System.out.println("=== HashMap Internals Exploration ===");

        // 1. Mostrar hashCode de diferentes strings
        System.out.println("\n--- Hash Codes ---");
        // TODO: Mostrar hashCode de "Juan", "Maria", "Pedro"
        System.out.println("Juan".hashCode());
        System.out.println("María".hashCode());
        System.out.println("Pedro".hashCode());


        // 2. Calcular array index manualmente
        System.out.println("\n--- Array Index Calculation ---");
        // TODO: Calcular array index para capacity = 16
        // Formula: Math.abs(hashCode) % 16
        int ubicationA = Math.abs("Juan".hashCode()) % 16;
        int ubicationB = Math.abs("María".hashCode()) % 16;
        int ubicationC = Math.abs("Pedro".hashCode()) % 16;

        System.out.println("Array Index - Juan : " + ubicationA);
        System.out.println("Array Index - María : " + ubicationB);
        System.out.println("Array Index - Pedro : " + ubicationC);


        // 3. Provocar colisión intencionalmente
        System.out.println("\n--- Collision Demo ---");
        Map<String, String> collisionMap = new HashMap<>();
        // TODO: Usar "AaAa" y "BBBB" (tienen mismo hashCode!)
        collisionMap.put("AaAa", "A");
        collisionMap.put("BBBB", "B");

        System.out.println("HashCode AaAa: " + "AaAa".hashCode());
        System.out.println("HashCode BBBB: " + "BBBB".hashCode());


        // 4. Verificar que HashMap maneja la colisión
        System.out.println("\n--- Collision Handling ---");
        // TODO: Mostrar que ambos valores están almacenados
        collisionMap.forEach((key, value) -> {
            System.out.println("Key: " + key + " Value: " + value);
        });

    }

}

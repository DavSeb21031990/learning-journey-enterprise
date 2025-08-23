package com.example.entities;

public class Employee {
    private String id;
    private String name;
    private String department;
    private double salary;
    private int experience;
    private String level; // Junior, Mid, Senior

    public Employee(String id, String name, String department, double salary, int experience) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.experience = experience;
    }

    public Employee(String id, String name, String department, double salary, int experience, String level) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.experience = experience;
        this.level = level;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getExperience() { return experience; }
    public String getLevel() { return level; }

    @Override
    public String toString() {
        return name + " (" + department + ", $" + salary + ", " + level + ")";
    }
}

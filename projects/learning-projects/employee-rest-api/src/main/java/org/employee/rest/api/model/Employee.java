package org.employee.rest.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @Column(name = "employee_id", length = 10)
    private String id;

    @Column(name = "employee_name", nullable = false, length = 50)
    private String name;

    @Column(name = "department", nullable = false, length = 30)
    private String department;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "experience_years", nullable = false)
    private Integer experience;

    @Column(name = "employee_level", length = 10)
    private String level; // Junior, Mid, Senior

    @Builder.Default
    @Column(name = "createAt", nullable = false, updatable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Builder.Default
    @Column(name = "updateAt")
    private LocalDateTime updateAt = LocalDateTime.now();

    public Employee(String id, String name, String department, Double salary, Integer experience, String level) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.experience = experience;
        this.level = level;
    }

    @PrePersist
    public void onCreate(){
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }
}

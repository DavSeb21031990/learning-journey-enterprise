package org.employee.rest.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "employee_id", length = 10)
    private String id;

    @Column(name = "employee_name", nullable = false, length = 50)
    private String name;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "experience_years", nullable = false)
    private Integer experience;

    @Column(name = "employee_level", length = 10)
    private String level; // Junior, Mid, Senior

    @Builder.Default
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String id, String name, Double salary, Integer experience, String level) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experience = experience;
        this.level = level;
    }

    @PrePersist
    public void onCreate() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public String getDepartmentName(){
        return this.department != null ? this.department.getName() : "";
    }

}

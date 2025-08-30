package org.employee.rest.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "departments")
public class Department {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(length = 200)
    private String description;

    private Double budget;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    @Builder.Default
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "update_at")
    private LocalDateTime updateAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
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
}

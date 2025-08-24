package org.employee.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    private String id;
    private String name;
    private String department;
    private double salary;
    private int experience;
    private String level; // Junior, Mid, Senior

}

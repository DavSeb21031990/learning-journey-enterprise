package org.employee.rest.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private String id;
    private String name;
    private String department;
    private double salary;
    private int experience;
    private String level;

}

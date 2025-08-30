package org.employee.rest.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2-50 characters")
    private String name;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", message = "Salary must be positive")
    @DecimalMax(value = "1000000.0", message = "Salary cannot exceed 1M")
    private Double salary;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 50, message = "Experience cannot exceed 50 years")
    private Integer experience;

    @NotBlank(message = "Level is required")
    @Pattern(regexp = "Junior|Mid|Senior", message = "Level must be Junior, Mid, or Senior")
    private String level;

    @NotBlank(message = "Department ID is required")
    private String departmentId;

}

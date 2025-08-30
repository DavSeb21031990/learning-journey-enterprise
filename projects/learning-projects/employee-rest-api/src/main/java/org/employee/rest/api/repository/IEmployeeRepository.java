package org.employee.rest.api.repository;

import org.employee.rest.api.dto.analytics.DepartmentCountDto;
import org.employee.rest.api.dto.analytics.DepartmentSalaryDto;
import org.employee.rest.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {

    // TODO: Refactor analytics - move department-centric queries to DepartmentService
    // TODO: Consider AnalyticsService for cross-domain queries

    //List<Employee> findByDepartment(String department);
    List<Employee> findBySalaryGreaterThan(Double salary);

    //List<Employee> findByDepartmentAndSalaryGreaterThan(String department, Double salary);
    List<Employee> findByExperienceGreaterThan(Integer experience);

    List<Employee> findByLevel(String level);

    Optional<Employee> findByName(String name);

    @Query("SELECT new org.employee.rest.api.dto.analytics.DepartmentCountDto(d.name, count(e)) " +
            "FROM Employee e JOIN e.department d GROUP BY d.name")
    List<DepartmentCountDto> getDepartmentCounts();

    @Query("SELECT new org.employee.rest.api.dto.analytics.DepartmentSalaryDto(d.name, AVEG(e.salary)) FROM Employee e JOIN e.department d GROUP BY d.name")
    List<DepartmentSalaryDto> getAverageSalaryByDepartment();

    @Query("SELECT e FROM Employee e WHERE e.salary > :threshold")
    List<Employee> findByHighPerformers(@Param("threshold") Double threshold);

}

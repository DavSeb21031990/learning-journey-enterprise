package org.employee.rest.api.repository;

import org.employee.rest.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByDepartment(String department);
    List<Employee> findBySalaryGreaterThan(Double salary);
    List<Employee> findByDepartmentAndSalaryGreaterThan(String department, Double salary);
    List<Employee> findByExperienceGreaterThan(Integer experience);
    List<Employee> findByLevel(String level);

    Optional<Employee> findByName(String name);

    @Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
    List<Object[]> getDepartmentCounts();

    @Query("SELECT e.department, AVEG(e.salary) FROM Employee e GROUP BY e.department")
    List<Object[]> getAverageSalaryByDepartment();

    @Query("SELECT e FROM Employee e WHERE e.salary > :threshold")
    List<Employee> findByHighPerformers(@Param("threshold") Double threshold);

}

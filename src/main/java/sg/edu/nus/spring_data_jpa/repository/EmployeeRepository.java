package sg.edu.nus.spring_data_jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.spring_data_jpa.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	• Find by partial name (ignoring case).
	List<Employee> findByNameContainingIgnoreCase(String name);

//	• Custom @Query: Fetch an Employee and their Department by Employee ID (findByIdWithDepartment).	
	@Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id")
    Optional<Employee> findEmployeeAndDepartmentByEmployeeId(@Param("id") Long id);
	
//	• Find employees by departmentId.
	List<Employee> findByDepartmentId(Long departmentId);
	
//	• Custom @Query: Fetch an Employee and their Projects by Employee ID (findByIdWithProjects).
	@Query("SELECT e FROM Employee e JOIN FETCH e.projects WHERE e.id = :id")
    Optional<Employee> findEmployeeAndProjectsByEmployeeId(@Param("id") Long id);

//	• Find employees by a projectId.
	@Query("SELECT e FROM Employee e JOIN e.projects p WHERE p.id = :projectId")
    List<Employee> findByProjectId(@Param("projectId") Long projectId);

//	• Custom @Query: Fetch an Employee and their Courses by Employee ID (findByIdWithCourses).
	@Query("SELECT e FROM Employee e JOIN FETCH e.courses WHERE e.id = :id")
    Optional<Employee> findByIdWithCourses(@Param("id") Long id);
}
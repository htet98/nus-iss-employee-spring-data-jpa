package sg.edu.nus.spring_data_jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.spring_data_jpa.model.Department;
import sg.edu.nus.spring_data_jpa.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	4. EmployeeRepository
//	• Find by partial name (ignoring case).
	List<Employee> findByNameContainingIgnoreCase(String name);
	
//	• Custom @Query: Fetch an Employee and their Department by Employee ID (findByIdWithDepartment).
	@Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id")
	Optional<Employee> findByIdWithDepartment(@Param("id") Long id);
//	• Find employees by departmentId.
	
	
//	• Custom @Query: Fetch an Employee and their Projects by Employee ID (findByIdWithProjects).
//	@Query("SELECT e, e. FROM Employee e LEFT JOIN FETCH e.projects WHERE e.id = :id")
//	Optional<Employee> findByIdWithProjects(@Param("id") Long id);
	
//	• Find employees by a projectId.
	
//	• Custom @Query: Fetch an Employee and their Courses by Employee ID (findByIdWithCourses).
//
}

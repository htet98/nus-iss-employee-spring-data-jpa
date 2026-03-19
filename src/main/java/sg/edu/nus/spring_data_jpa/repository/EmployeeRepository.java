package sg.edu.nus.spring_data_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.spring_data_jpa.model.Department;
import sg.edu.nus.spring_data_jpa.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	4. EmployeeRepository
//	• Find by partial name (ignoring case).
	List<Employee> findByNameContainingIgnoreCase(String name);
	
//	• Custom @Query: Fetch an Employee and their Department by Employee ID (findByIdWithDepartment).
//	• Find employees by departmentId.
	
//	• Custom @Query: Fetch an Employee and their Projects by Employee ID (findByIdWithProjects).
	
//	• Find employees by a projectId.
	
//	• Custom @Query: Fetch an Employee and their Courses by Employee ID (findByIdWithCourses).
//
}

package sg.edu.nus.jpa_workshop.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.jpa_workshop.model.Courses;
import sg.edu.nus.jpa_workshop.model.Department;
import sg.edu.nus.jpa_workshop.model.Employee;
import sg.edu.nus.jpa_workshop.model.Project;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findById(Long Id);
	
	Optional<Employee> findByName(String name);
	
	@Query("SELECT e from Employee e WHERE e.employeeId = :eid")
	ArrayList<Courses> findByIdWithDepartment(@Param("eid") String eid);
	
	List<Department> findByDepartmentId(Long departmentId);
	
	@Query("SELECT e from Employee e WHERE e.employeeId = :eid")
	ArrayList<Project> findByIdWithProjects(@Param("eid") String eid);
	
	List<Project> findByprojectId(Long projectId);
	
	@Query("SELECT e from Employee e WHERE e.employeeId = :eid")
	ArrayList<Project> findByIdWithCourses(@Param("eid") String eid);
}

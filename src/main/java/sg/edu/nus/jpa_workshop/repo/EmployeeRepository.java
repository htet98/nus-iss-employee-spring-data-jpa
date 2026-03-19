package sg.edu.nus.jpa_workshop.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.jpa_workshop.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findById(Long employee_id);
	
	Optional<Employee> findByName(String name);
	List<Employee> findByNameContainingIgnoreCase(String name);
	
	@Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id")
	Optional<Employee> findByIdWithDepartment(@Param("id") Long id);
	
	List<Employee> findByDepartmentId(Long department_id);
	
	@Query("SELECT e FROM Employee e JOIN FETCH e.projects WHERE e.id = :id")
	Optional<Employee> findByIdWithProjects(@Param("id") Long id);
	
	List<Employee> findByProjectsId(Long project_id);
	
	@Query("SELECT e FROM Employee e JOIN FETCH e.courses WHERE e.id = :id")
	Optional<Employee> findByIdWithCourses(@Param("id") Long id);
}

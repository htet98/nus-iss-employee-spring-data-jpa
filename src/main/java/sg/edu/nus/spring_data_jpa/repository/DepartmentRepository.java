package sg.edu.nus.spring_data_jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.spring_data_jpa.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
//	3. DepartmentRepository
//	• Find by exact name.
	Department findByName(String name);
	
//	• Find by partial name (ignoring case).
	List<Department> findByNameContainingIgnoreCase(String name);
	
//	• Custom @Query: Fetch a Department and its assigned Employee by Department ID (findByIdWithEmployee).
//	@Query("SELECT DISTINCT d FROM Department d JOIN FETCH d.employees WHERE p.id = :id")
//	Optional<Project> findByIdWithEmployees(Long id);
	@Query("SELECT d FROM Department d LEFT JOIN FETCH d.employee WHERE d.id = :id")
	Optional<Department> findByIdWithEmployee(@Param("id") Long id);
	
	@Query("SELECT d FROM Department d JOIN d.employee e WHERE e.id = :id")
	Optional<Department> findByEmployeeId(@Param("id") Long id);
	
//	• Custom @Query: Write a query that returns a boolean checking if a department currently has an employee assigned (hasEmployee).
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
		       "FROM Employee e WHERE e.department.id = :id")
		boolean hasEmployee(@Param("id") Long departmentId);
}

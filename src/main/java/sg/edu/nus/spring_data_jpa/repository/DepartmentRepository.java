package sg.edu.nus.spring_data_jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.spring_data_jpa.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

//	Find by exact name.
	List<Department> findByName(String name);
	
//  Find by partial name (ignoring case).
	List<Department> findByNameContainingIgnoreCase(String name);
	
//	Custom @Query: Fetch a Department and its assigned Employee by Department ID (findByIdWithEmployee).
	@Query("SELECT d FROM Department d JOIN FETCH d.employee WHERE d.id = :id")
	Optional<Department> findByIdWithEmployee(Long id);
	
//	Custom @Query: Write a query that returns a boolean checking if a department currently has an employee assigned (hasEmployee).
	@Query("SELECT count(e) > 0 FROM Employee e WHERE e.department.id = :id")
	boolean hasEmployee(Long id);
}
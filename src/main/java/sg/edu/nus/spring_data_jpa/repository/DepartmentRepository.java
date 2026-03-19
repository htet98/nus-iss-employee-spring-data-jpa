package sg.edu.nus.spring_data_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.spring_data_jpa.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
//	3. DepartmentRepository
//	• Find by exact name.
	Department findByName(String name);
	
//	• Find by partial name (ignoring case).
	List<Department> findByNameContainingIgnoreCase(String name);
	
//	• Custom @Query: Fetch a Department and its assigned Employee by Department ID (findByIdWithEmployee).
//	• Custom @Query: Write a query that returns a boolean checking if a department currently has an employee assigned (hasEmployee).

}

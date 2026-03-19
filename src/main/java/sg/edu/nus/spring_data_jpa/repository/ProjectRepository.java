package sg.edu.nus.spring_data_jpa.repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.spring_data_jpa.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

//	2. ProjectRepository
//	• Find by exact project name.
	Project findByName(String name);
	
//	• Find by partial project name (ignoring case).
	List<Project> findByNameContainingIgnoreCase(String name);
	
//	• Find projects ending after a specific date.
	List<Project> findByEndDateAfter(LocalDate endDate);
	
//	• Custom @Query: Find projects within a specific start and end date range
//	(findByDateRange).
//	• Custom @Query: Fetch a Project and its assigned Employees by Project ID
//	(findByIdWithEmployees).
	
//	• Find projects by an employeeId.
//	List<Project> findByEmployeeId(Long employee_id);
}

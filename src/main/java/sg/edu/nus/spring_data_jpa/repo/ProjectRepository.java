package sg.edu.nus.spring_data_jpa.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.spring_data_jpa.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

//	Find by exact project name.
	List<Project> findByName(String name);
	
//	• Find by partial project name (ignoring case).
	List<Project> findByNameContainingIgnoreCase(String name);
	
//	• Find projects ending after a specific date.
	List<Project> findByEndDateAfter(LocalDate endDate);
	
//	• Custom @Query: Find projects within a specific start and end date range (findByDateRange).
	@Query("SELECT p FROM Project p WHERE p.startDate >= :startDate AND p.endDate <= :endDate")
	List<Project> findByDateRange(LocalDate startDate, LocalDate endDate);
	
//	• Custom @Query: Fetch a Project and its assigned Employees by Project ID (findByIdWithEmployees).
	@Query("SELECT p FROM Project p JOIN FETCH p.employees WHERE p.id = :id")
	Optional<Project> findByIdWithEmployees(Long id);
	
//	• Find projects by an employeeId.
	Optional<Project> findAllById(Long id);
}

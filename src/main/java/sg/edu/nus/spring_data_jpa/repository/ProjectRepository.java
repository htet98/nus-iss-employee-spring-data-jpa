package sg.edu.nus.spring_data_jpa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
//	This logic returns projects that:
//		Start before the given endDate
//		End after the given startDate
	@Query("SELECT p FROM Project p WHERE p.startDate <= :endDate AND p.endDate >= :startDate")
	List<Project> findByDateRange(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

//	• Custom @Query: Fetch a Project and its assigned Employees by Project ID
//	(findByIdWithEmployees).
	@Query("SELECT DISTINCT p FROM Project p JOIN FETCH p.employees WHERE p.id = :id")
	Optional<Project> findByIdWithEmployees(Long id);

//	• Find projects by an employeeId.
//	List<Project> findByEmployeeId(Long employeeId);
}

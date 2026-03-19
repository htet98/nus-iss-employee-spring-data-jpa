package sg.edu.nus.jpa_workshop.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.jpa_workshop.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>
{
	Optional<Project> findById(Long project_id);
	Optional<Project> findByName(String name);
	List<Project> findByNameContainingIgnoreCase(String name);
	List<Project> findByEndDateAfter(LocalDate date);
	@Query("SELECT p FROM Project p WHERE p.startDate >= :startDate AND p.endDate <= :endDate")
	List<Project> findByDateRange(@Param("startDate") LocalDate startDate,
	                             @Param("endDate") LocalDate endDate);
	@Query("SELECT p FROM Project p JOIN FETCH p.employees WHERE p.id = :id")
	Optional<Project> findByIdWithEmployees(@Param("id") Long id);
	List<Project> findByEmployeesId(Long employee_id);
}

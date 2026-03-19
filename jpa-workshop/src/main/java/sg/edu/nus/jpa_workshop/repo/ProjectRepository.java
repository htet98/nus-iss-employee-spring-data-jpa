package sg.edu.nus.jpa_workshop.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.jpa_workshop.model.Employee;
import sg.edu.nus.jpa_workshop.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>
{
	Optional<Project> findById(Long Id);
	Optional<Project> findByExactPName(String name);
	Optional<Project> findByPNameIgnoreCase(String name);
	Optional<Project> findAfterSpecificDate(LocalDate endDate);
	@Query("SELECT p from Project p WHERE p.startDate >= :startDate AND p.endDate <= :endDate")
	ArrayList<Project> findByDateRange(@Param("eid") String eid);
	
	List<Employee> findByEmployeeId(long id);
}

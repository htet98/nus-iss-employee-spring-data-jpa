package sg.edu.nus.empdemo.repository;

import sg.edu.nus.empdemo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByName(String name);
    List<Project> findByNameContainingIgnoreCase(String name);
    List<Project> findByEndDateAfter(LocalDate date);
    List<Project> findByEmployeesId(Long employeeId);

    @Query("SELECT p FROM Project p WHERE p.startDate >= ?1 AND p.endDate <= ?2")
    List<Project> findByDateRange(LocalDate start, LocalDate end);

    @Query("SELECT p FROM Project p JOIN FETCH p.employees WHERE p.id = ?1")
    Optional<Project> findByIdWithEmployees(Long id);
}
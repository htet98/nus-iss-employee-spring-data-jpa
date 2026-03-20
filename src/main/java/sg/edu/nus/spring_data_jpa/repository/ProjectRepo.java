package sg.edu.nus.spring_data_jpa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.spring_data_jpa.model.Project;

//Project Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

Optional<ProjectRepo> findByName(String name);

List<ProjectRepo> findByNameContainingIgnoreCase(String name);

List<ProjectRepo> findByEndDateAfter(LocalDate date);

@Query("SELECT p FROM Project p WHERE p.startDate >= :start AND p.endDate <= :end")
List<ProjectRepo> findByDateRange(@Param("start") LocalDate start, @Param("end") LocalDate end);

@Query("SELECT p FROM Project p JOIN FETCH p.employees WHERE p.id = :id")
Optional<ProjectRepo> findByIdWithEmployees(@Param("id") Long id);

List<ProjectRepo> findByEmployeesId(Long employeeId);

Project save(Project proj);
}



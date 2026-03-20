package sg.edu.nus.empdemo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//Project Repository
public interface project extends JpaRepository<project, Long> {

Optional<project> findByName(String name);

List<project> findByNameContainingIgnoreCase(String name);

List<project> findByEndDateAfter(LocalDate date);

@Query("SELECT p FROM Project p WHERE p.startDate >= :start AND p.endDate <= :end")
List<project> findByDateRange(@Param("start") LocalDate start, @Param("end") LocalDate end);

@Query("SELECT p FROM Project p JOIN FETCH p.employees WHERE p.id = :id")
Optional<project> findByIdWithEmployees(@Param("id") Long id);

List<project> findByEmployeesId(Long employeeId);
}



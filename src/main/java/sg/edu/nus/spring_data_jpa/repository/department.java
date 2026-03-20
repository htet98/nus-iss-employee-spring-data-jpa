package sg.edu.nus.empdemo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.empdemo.model.Course;
import sg.edu.nus.empdemo.model.Department;
import sg.edu.nus.empdemo.model.Project;

//Department Repository
public interface department extends JpaRepository<department, Long> {

Optional<department> findByName(String name);

List<department> findByNameContainingIgnoreCase(String name);

@Query("SELECT d FROM Department d JOIN FETCH d.employee WHERE d.id = :id")
Optional<department> findByIdWithEmployee(@Param("id") Long id);

@Query("SELECT CASE WHEN COUNT(d.employee) > 0 THEN true ELSE false END FROM Department d WHERE d.id = :id")
boolean hasEmployee(@Param("id") Long id);
}


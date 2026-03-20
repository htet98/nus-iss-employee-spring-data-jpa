package sg.edu.nus.empdemo.repository;

import sg.edu.nus.empdemo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameContainingIgnoreCase(String name);
    List<Course> findByStartsAfter(LocalDate date);
    List<Course> findByDurationInMonthsLessThanEqual(Double duration);
    List<Course> findByEmployeeId(Long employeeId);
    List<Course> findByEmployeeIdAndStartsAfter(Long employeeId, LocalDate date);

    @Query("SELECT c FROM Course c JOIN FETCH c.employee WHERE c.id = ?1")
    Optional<Course> findByIdWithEmployee(Long id);
    
}

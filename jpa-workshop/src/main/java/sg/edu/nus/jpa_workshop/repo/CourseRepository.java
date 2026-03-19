package sg.edu.nus.jpa_workshop.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.jpa_workshop.model.Courses;
import sg.edu.nus.jpa_workshop.model.Employee;

public interface CourseRepository extends JpaRepository<Courses, Long> {
	List<Courses> findByNameContainingIgnoreCase(String name);
	List<Courses> findByStartsAfter(LocalDate startDate);
	List<Courses> findByDurationInMonthsLessThanEqual(int duration);
	List<Courses> findByEmployeeId(Long employeeId);
	Optional<Courses> findByLocalDate(LocalDate startDate);
	
	@Query("SELECT c FROM Courses c JOIN FETCH c.employee WHERE c.id = :id")
	Optional<Courses> findByIdWithEmployee(@Param("id") Long id);
	
	List<Courses> findByEmployeeIdAndStartsAfter(Long employeeId, LocalDate startDate);
	
}

package sg.edu.nus.jpa_workshop.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.jpa_workshop.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Long> {
	@Query("SELECT c FROM Courses c WHERE UPPER(c.name) LIKE UPPER(:name) ESCAPE '\\'")
	List<Courses> findByNameContainingIgnoreCase(String name);
	List<Courses> findByStartsAfter(LocalDate startDate);
	List<Courses> findByDurationInMonthsLessThanEqual(int duration);

	@Query("SELECT c FROM Courses c WHERE c.employee.id = :employee_id")
	List<Courses> findByEmployeeId(Long employee_id);
	//Optional<Courses> findByLocalaDate(LocalDate startDate);
	
	@Query("SELECT c FROM Courses c JOIN FETCH c.employee WHERE c.id = :id")
	Optional<Courses> findByIdWithEmployee(@Param("id") Long id);
	
	List<Courses> findByEmployeeIdAndStartsAfter(Long employee_id, LocalDate startDate);
	
}

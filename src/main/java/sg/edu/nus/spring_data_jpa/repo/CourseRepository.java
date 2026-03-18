package sg.edu.nus.spring_data_jpa.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.spring_data_jpa.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Long> {
//	Find courses where the name contains a specific string, ignoring case.
	List<Courses> findByNameContainingIgnoreCase(String name);
	
//	• Find courses starting after a specific date.
	List<Courses> findByStartsAfter(LocalDate startDate);
	
//	• Find courses by maximum duration.
	List<Courses> findByDurationInMonthsLessThanEqual(int duration);
	
//	• Find courses by employeeId.
	List<Courses> findAllById(Long id);
	
//	• Custom @Query: Fetch a Course and its associated Employee in a single query by the course ID to avoid N+1 issues (findByIdWithEmployee).
	@Query("SELECT c FROM Courses c JOIN FETCH c.employee WHERE c.id = :id")
	List<Courses> findByIdWithEmployee(@Param("id") Long id);
	
//	• Find courses by employeeId that start after a specific date.
	List<Courses> findByEmployeeIdAndStartsAfter(Long empId, LocalDate startDate);
}

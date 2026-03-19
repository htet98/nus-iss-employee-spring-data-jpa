package sg.edu.nus.spring_data_jpa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.spring_data_jpa.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
//	1. CourseRepository
//	• Find courses where the name contains a specific string, ignoring case.
	List<Course> findByNameLike(String name);
	List<Course> findByNameContainingIgnoreCase(String name);
	
//	• Find courses starting after a specific date.
	List<Course> findByStartsAfter(LocalDate starts);
	
//	Find courses by maximum duration.
	Optional<Course> findTopByOrderByDurationInMonthsDesc();
	
//	• Find courses by employeeId.
	List<Course> findByEmployee_Id(Long employee_id);
	
//	• Custom @Query: Fetch a Course and its associated Employee in a single query by the course ID to avoid N+1 issues (findByIdWithEmployee).
	@Query("SELECT c FROM Course c JOIN FETCH c.employee WHERE c.id = :id")
	Optional<Course> findByIdWithEmployee(@Param("id") Long id);
	
//	Find courses by employeeId that start after a specific date.
	List<Course> findByEmployeeIdAndStartsAfter(Long employeeid, LocalDate starts);
//	List<Course> findByEmployee_IdAndStartsAfter(Long employee_id, LocalDate starts);
}

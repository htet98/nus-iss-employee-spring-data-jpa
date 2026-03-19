package sg.edu.nus.jpa_workshop.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.jpa_workshop.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findById(Long department_id);
	Optional<Department> findByName(String name);
	List<Department> findByNameContainingIgnoreCase(String name);
	
	
	@Query("SELECT d FROM Department d JOIN FETCH d.employee WHERE d.id = :id")
	Optional<Department> findByIdWithEmployee(@Param("id") Long id);
	
	@Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.department.id = :deptId")
	boolean hasEmployee(@Param("deptId") Long department_id);
}

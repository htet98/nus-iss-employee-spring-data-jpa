package sg.edu.nus.jpa_workshop.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.jpa_workshop.model.Department;
import sg.edu.nus.jpa_workshop.model.Employee;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findById(Long id);
	Optional<Department> findByExactName(String name);
	Optional<Department> findByPartialNameIC(String name);
	
	@Query("SELECT d from Department d WHERE d.employeeId = :eid")
	ArrayList<Department> findByIdWithEmployee(@Param("eid") String eid);
	
	@Query("SELECT e from Employee e WHERE e.department.id = :deptId")
	boolean hasEmployee (@Param("deptId") Long id);
}

package sg.edu.nus.empdemo.repository;

import sg.edu.nus.empdemo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByName(String name);
    List<Department> findByNameContainingIgnoreCase(String name);

    @Query("SELECT d FROM Department d JOIN FETCH d.employee WHERE d.id = ?1")
    Optional<Department> findByIdWithEmployee(Long id);

    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.department.id = ?1")
    boolean hasEmployee(Long departmentId);
    
}

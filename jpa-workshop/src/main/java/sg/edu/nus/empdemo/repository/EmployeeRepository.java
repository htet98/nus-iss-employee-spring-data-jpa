package sg.edu.nus.empdemo.repository;

import sg.edu.nus.empdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByDepartmentId(Long deptId);
    List<Employee> findByProjectsId(Long projectId);

    @Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = ?1")
    Optional<Employee> findByIdWithDepartment(Long id);

    @Query("SELECT e FROM Employee e JOIN FETCH e.projects WHERE e.id = ?1")
    Optional<Employee> findByIdWithProjects(Long id);

    @Query("SELECT e FROM Employee e JOIN FETCH e.courses WHERE e.id = ?1")
    Optional<Employee> findByIdWithCourses(Long id);
    
}

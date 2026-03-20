//=======================
//PACKAGE: sg.edu.nus.empdemo.repository
//=======================

package sg.edu.nus.empdemo.repository;

import sg.edu.nus.empdemo.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.*;

//Employee Repository
public interface employee extends JpaRepository<Employee, Long> {

 List<Employee> findByNameContainingIgnoreCase(String name);

 @Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id")
 Optional<Employee> findByIdWithDepartment(@Param("id") Long id);

 List<Employee> findByDepartmentId(Long departmentId);

 @Query("SELECT e FROM Employee e JOIN FETCH e.projects WHERE e.id = :id")
 Optional<Employee> findByIdWithProjects(@Param("id") Long id);

 List<Employee> findByProjectsId(Long projectId);

 @Query("SELECT e FROM Employee e JOIN FETCH e.courses WHERE e.id = :id")
 Optional<Employee> findByIdWithCourses(@Param("id") Long id);
}




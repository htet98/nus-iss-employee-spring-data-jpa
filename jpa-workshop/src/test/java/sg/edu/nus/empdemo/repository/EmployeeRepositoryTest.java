package sg.edu.nus.empdemo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;


import sg.edu.nus.empdemo.model.Course;
import sg.edu.nus.empdemo.model.Department;
import sg.edu.nus.empdemo.model.Employee;
import sg.edu.nus.empdemo.model.Project;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepo;

    private Employee savedEmployee;
    private Department savedDept;

    @BeforeEach
    void setUp() {
        // 1. Setup Department
        Department dept = new Department();
        dept.setName("IT");
        savedDept = entityManager.persistAndFlush(dept);

        // 2. Setup Employee
        Employee emp = new Employee();
        emp.setName("Cecil Zhou");
        emp.assignDepartment(savedDept);

        // 3. Setup Project
        Project proj = new Project();
        proj.setName("Cloud Migration");
        proj.setStartDate(LocalDate.now());
        entityManager.persist(proj);
        emp.joinProject(proj);

        // 4. Setup Course
        Course course = new Course();
        course.setName("Spring Boot 3");
        course.setDurationInMonths(1.0);
        course.setStarts(LocalDate.now());
        entityManager.persist(course);
        emp.enrollInCourse(course);

        savedEmployee = entityManager.persistAndFlush(emp);
        entityManager.clear(); // Clear cache to force JPA to hit the database for FETCH tests
    }

    @Test
    public void testFindByNameContainingIgnoreCase() {
        List<Employee> found = employeeRepo.findByNameContainingIgnoreCase("cecil");
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getName()).isEqualTo("Cecil Zhou");
    }

    @Test
    public void testFindByIdWithDepartment() {
        Optional<Employee> result = employeeRepo.findByIdWithDepartment(savedEmployee.getId());
        
        assertThat(result).isPresent();
        // This verifies the JOIN FETCH worked
        assertThat(result.get().getDepartment().getName()).isEqualTo("IT");
    }

    @Test
    public void testFindByIdWithProjects() {
        Optional<Employee> result = employeeRepo.findByIdWithProjects(savedEmployee.getId());
        
        assertThat(result).isPresent();
        assertThat(result.get().getProjects()).isNotEmpty();
        assertThat(result.get().getProjects().get(0).getName()).isEqualTo("Cloud Migration");
    }

    @Test
    public void testFindByDepartmentId() {
        List<Employee> results = employeeRepo.findByDepartmentId(savedDept.getId());
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Cecil Zhou");
    }

    @Test
    public void testFindByIdWithCourses() {
        Optional<Employee> result = employeeRepo.findByIdWithCourses(savedEmployee.getId());
        
        assertThat(result).isPresent();
        assertThat(result.get().getCourses()).hasSize(1);
        assertThat(result.get().getCourses().get(0).getName()).isEqualTo("Spring Boot 3");
    }
}
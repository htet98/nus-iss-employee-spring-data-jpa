package sg.edu.nus.spring_data_jpa.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import sg.edu.nus.spring_data_jpa.model.Courses;
import sg.edu.nus.spring_data_jpa.model.Department;
import sg.edu.nus.spring_data_jpa.model.Employee;
import sg.edu.nus.spring_data_jpa.model.Project;
import sg.edu.nus.spring_data_jpa.repository.DepartmentRepository;
import sg.edu.nus.spring_data_jpa.repository.EmployeeRepository;
import sg.edu.nus.spring_data_jpa.repository.ProjectRepo;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProjectRepo projectRepository;
    
    @Autowired private TestEntityManager entityManager;
    
    private Long employeeId;
    private Long departmentId;
    private Long projectId;
    
    private static final String EMPLOYEE_NAME = "John Doe";
    private static final String PROJECT_NAME = "Cloud Migration";
    private static final String DEPT_NAME_IT = "IT";
    private static final String COURSE_NAME = "Spring Boot";
    

    @BeforeEach
    void setUp() {
        Department dept = new Department();
        dept.setName(DEPT_NAME_IT);
        Department dept_saved = departmentRepository.save(dept);
        departmentId = dept_saved.getId();

        Project proj = new Project();
        proj.setName(PROJECT_NAME);
        Project project = projectRepository.save(proj);
        projectId = project.getId();

        Employee emp = new Employee();
        emp.setName(EMPLOYEE_NAME);
        emp.setDepartment(dept);

        Courses course = new Courses();
        course.setName(COURSE_NAME);
        course.setStartDate(LocalDate.now());
        course.setDurationInMonths(3);

        // Link everything
        emp.addProject(proj);
        emp.addCourse(course);

        Employee saved = employeeRepository.save(emp);
        employeeId = saved.getId();

        entityManager.flush();
        entityManager.clear();
    }
    
    @Test
    void testFindByNameContainingIgnoreCase() {
    	System.out.println("== START METHOD ==");
    	List<Employee> employeeList = employeeRepository.findByNameContainingIgnoreCase("john");
    	assertTrue(employeeList.size() > 0);
    	assertTrue(employeeList.get(0).getName().equals(EMPLOYEE_NAME));

    }
    
    @Test
    void testFindByDepartmentId() {
    	List<Employee> employeeList = employeeRepository.findByDepartmentId(departmentId);
    	assertTrue(employeeList.size() > 0);
    	assertTrue(employeeList.get(0).getName().equals(EMPLOYEE_NAME));
    }
    
    @Test
    void testFindEmployeeAndDepartmentByEmployeeId() {
        System.out.println("=== START RETRIEVAL TEST ===");
        Optional<Employee> result = employeeRepository.findEmployeeAndDepartmentByEmployeeId(employeeId);
        System.out.println("=== END RETRIEVAL TEST ===");
        
        assertThat(result).isPresent();
        assertThat(result.get().getDepartment().getName()).isEqualTo(DEPT_NAME_IT);
    }

    @Test
    void testFindEmployeeAndProjectsByEmployeeId() {
        Optional<Employee> result = employeeRepository.findEmployeeAndProjectsByEmployeeId(employeeId);
        assertThat(result).isPresent();
        assertThat(result.get().getProjects()).extracting(Project::getName).contains(PROJECT_NAME);
    }
    
    @Test
    void testFindByProjectId() {
    	List<Employee> result = employeeRepository.findByProjectId(projectId);
        assertThat(result.size() > 0);
        assertThat(result.get(0).getName()).isEqualTo(EMPLOYEE_NAME);
    }
    
    @Test
    void testFindByIdWithCourses() {
    	Optional<Employee> result = employeeRepository.findByIdWithCourses(employeeId);
    	assertThat(result).isPresent();
    	assertThat(result.get().getCourses().get(0).getName()).isEqualTo(COURSE_NAME);
    }
    
}
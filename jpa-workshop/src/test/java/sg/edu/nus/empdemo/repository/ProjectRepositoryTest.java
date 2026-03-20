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

import sg.edu.nus.empdemo.model.Employee;
import sg.edu.nus.empdemo.model.Project;

@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepo;

    private Project savedProject;

    @BeforeEach
    void setUp() {
        Project project = new Project();
        project.setName("Alpha Project");
        project.setStartDate(LocalDate.of(2026, 1, 1));
        project.setEndDate(LocalDate.of(2026, 12, 31));
        savedProject = entityManager.persistAndFlush(project);

        Employee emp = new Employee();
        emp.setName("Project Lead");
        emp.joinProject(savedProject);
        entityManager.persistAndFlush(emp);

        entityManager.clear();
    }

    @Test
    public void testFindByDateRange() {
        // Test custom @Query findByDateRange
        List<Project> projects = projectRepo.findByDateRange(
            LocalDate.of(2025, 12, 31), 
            LocalDate.of(2027, 1, 1)
        );
        assertThat(projects).hasSize(1);
        assertThat(projects.get(0).getName()).isEqualTo("Alpha Project");
    }

    @Test
    public void testFindByIdWithEmployees() {
        // Test JOIN FETCH query
        Optional<Project> result = projectRepo.findByIdWithEmployees(savedProject.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getEmployees()).isNotEmpty();
        assertThat(result.get().getEmployees().get(0).getName()).isEqualTo("Project Lead");
    }

    @Test
    public void testFindByEmployeesId() {
        // Find the employee id first
        Project p = entityManager.find(Project.class, savedProject.getId());
        Long empId = p.getEmployees().get(0).getId();

        List<Project> projects = projectRepo.findByEmployeesId(empId);
        assertThat(projects).hasSize(1);
    }
}

package sg.edu.nus.empdemo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import sg.edu.nus.empdemo.model.Department;
import sg.edu.nus.empdemo.model.Employee;

@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepo;

    private Department savedDept;

    @BeforeEach
    void setUp() {
        // Setup a Department
        Department dept = new Department();
        dept.setName("Human Resources");
        savedDept = entityManager.persistAndFlush(dept);

        // Setup an Employee and link to Department
        Employee emp = new Employee();
        emp.setName("Jane Doe");
        emp.assignDepartment(savedDept);
        entityManager.persistAndFlush(emp);

        entityManager.clear(); 
    }

    @Test
    public void testFindByName() {
        List<Department> found = departmentRepo.findByName("Human Resources");
        assertThat(found).hasSize(1);
    }

    @Test
    public void testFindByIdWithEmployee() {
        Optional<Department> result = departmentRepo.findByIdWithEmployee(savedDept.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getEmployee()).isNotNull();
        assertThat(result.get().getEmployee().getName()).isEqualTo("Jane Doe");
    }

    @Test
    public void testHasEmployee() {
        boolean hasEmp = departmentRepo.hasEmployee(savedDept.getId());
        assertThat(hasEmp).isTrue();
    }
}

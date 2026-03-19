package sg.edu.nus.spring_data_jpa.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import sg.edu.nus.spring_data_jpa.model.Employee;
import sg.edu.nus.spring_data_jpa.repository.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	private TestEntityManager em;
	
	@BeforeEach
	public void setUp() {
		Employee emp1 = new Employee();
		emp1.setName("J Bondie");
		Employee savedEmp = empRepo.save(emp1);	
		
		em.persist(savedEmp);
		em.flush();
	}

	@DisplayName("Test employee Creation")
	@Test
	void saveEmployee() {
		Employee emp1 = new Employee();
		emp1.setName("James Bond");
		Employee savedEmp = empRepo.save(emp1);
		System.out.println("Test employee Creation");
		System.out.println(savedEmp.getId());
		System.out.println(savedEmp);
		em.persist(savedEmp);
//		em.flush();
		assertThat(savedEmp.getId()).isNotNull().isPositive();
	}
	
	@Test
	@DisplayName("Test find all Users")
	void find() {
		List<Employee> employees = empRepo.findAll();
		System.out.println("Test find all User");
		System.out.println(employees.size());
		System.out.println(employees);
		assertThat(employees.size() > 0);
	}
}

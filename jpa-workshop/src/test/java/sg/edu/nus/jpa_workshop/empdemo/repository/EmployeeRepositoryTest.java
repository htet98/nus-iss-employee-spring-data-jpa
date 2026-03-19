package sg.edu.nus.jpa_workshop.empdemo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import sg.edu.nus.jpa_workshop.model.Employee;
import sg.edu.nus.jpa_workshop.repo.EmployeeRepository;

@DataJpaTest
public class EmployeeRepositoryTest {
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private EmployeeRepository eRepository;
	
	@Test
	void findByName_shouldReturnMatchingEmployee() {
	
		Employee employee = new Employee();
		em.persistAndFlush(employee);
		em.clear(); // ensures we read from DB, not first-level cache
		
		Optional<Employee> result = eRepository.findByName("Junior");
		
		assertThat(result).isPresent();
	}
}
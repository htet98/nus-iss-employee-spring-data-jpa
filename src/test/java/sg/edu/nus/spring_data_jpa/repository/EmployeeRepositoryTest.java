package sg.edu.nus.spring_data_jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import sg.edu.nus.spring_data_jpa.model.Course;
import sg.edu.nus.spring_data_jpa.model.Department;
import sg.edu.nus.spring_data_jpa.model.Employee;
import sg.edu.nus.spring_data_jpa.model.Project;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository eRepo;
	@Autowired
	DepartmentRepository dRepo;
	@Autowired
	ProjectRepository projRepo;
	@Autowired
	CourseRepository CourseRepo;

	@Autowired
	private TestEntityManager em;

	@BeforeTestClass
	public void setUp() {
//		Employee emp1 = new Employee();
//		emp1.setName("J Bondie");
//		Employee savedEmp = empRepo.save(emp1);	
//		
//		em.persist(savedEmp);
//		em.flush();

		// create employee 1
		Employee emp1 = new Employee();
		emp1.setName("James Bond");
		Department dept1 = new Department();
		dept1.setName("ISS");
		emp1.setDepartment(dept1);

		Project p1 = new Project();
		p1.setName("Project X");
		p1.setStartDate(LocalDate.of(2026, 1, 1));
		p1.setEndDate(LocalDate.of(2026, 10, 31));
		p1.setDescription("this is desc");
		emp1.setProjects(Set.of(p1));

		Course course1 = new Course();
		course1.setName("Java");
		course1.setDurationInMonths(12);
		course1.setStarts(LocalDate.of(2026, 10, 10));

		Course course2 = new Course();
		course2.setName("Python2");
		course2.setDurationInMonths(3);
		course2.setStarts(LocalDate.of(2026, 1, 10));
//		emp1.setCourses(List.of(course1, course2));
		emp1.addCourse(course1);
		emp1.addCourse(course2);
//		eRepo.save(emp1);
		Employee savedEmp = eRepo.save(emp1);
		em.persist(savedEmp);
		em.flush();

		// create employee 2
		Employee emp2 = new Employee();
		emp2.setName("John Doe");
		Department dept2 = new Department();
		dept2.setName("NUSISS");
		emp2.setDepartment(dept2);

		Project p2 = new Project();
		p2.setName("Project-Z");
		p2.setStartDate(LocalDate.of(2026, 1, 1));
		p2.setEndDate(LocalDate.of(2026, 1, 31));
		p2.setDescription("this is desc");
		emp2.setProjects(Set.of(p2));

		Course course3 = new Course();
		course3.setName("Agile");
		course3.setDurationInMonths(1);
		course3.setStarts(LocalDate.of(2026, 2, 1));

		Course course4 = new Course();
		course4.setName(".NET");
		course4.setDurationInMonths(2);
		course4.setStarts(LocalDate.of(2026, 4, 1));
		emp2.addCourse(course3);
		emp2.addCourse(course4);
//		eRepo.save(emp2);
		Employee savedEmp2 = eRepo.save(emp2);
		em.persist(savedEmp2);
		em.flush();

	}

//	@DisplayName("Test employee Creation")
//	@Test
//	void saveEmployee() {
//		Employee emp1 = new Employee();
//		emp1.setName("James Bond");
//		Employee savedEmp = eRepo.save(emp1);
//		System.out.println("Test employee Creation");
//		System.out.println(savedEmp.getId());
//		System.out.println(savedEmp);
//		em.persist(savedEmp);
//		assertThat(savedEmp.getId()).isNotNull().isPositive();
//	}

//	@Test
//	@DisplayName("Test find all Users")
//	void find1() {
//		List<Employee> employees = eRepo.findAll();
//		System.out.println("Test find all User");
//		System.out.println(employees.size());
//		System.out.println(employees);
//		assertThat(employees.size() > 0);
//	}

//	1. CourseRepository
//	• Find courses where the name contains a specific string, ignoring case.
	@Test
	@DisplayName("Find courses where the name contains a specific string, ignoring case")
	void findCourseContainName() {
		String searchtext = "jav";
		List<Course> courses = CourseRepo.findByNameContainingIgnoreCase(searchtext);
//		System.out.println(courses);
		assertThat(courses.size() > 0);
	}

//	Find courses starting after a specific date.
	@Test
	@DisplayName("Find courses starting after a specific date.")
	void findCoursesStartAfter() {
		LocalDate startDate = LocalDate.of(2026, 5, 1);
		List<Course> c3 = CourseRepo.findByStartsAfter(startDate);
		System.out.println(c3);
		assertThat(c3.size() > 0);
	}

	@Test
	@DisplayName("Find courses starting after a specific date.")
	void findCoursesStartingAfterSpecificDate() {
		LocalDate startDate = LocalDate.of(2026, 5, 1);
		List<Course> c3 = CourseRepo.findByStartsAfter(startDate);
		System.out.println(c3);
		assertThat(c3.size() > 0);
	}
	
//	• Find courses by maximum duration.
	@Test
	@DisplayName("Find courses by maximum duration.")
	void findCoursesByMaximumDuration() {
		Optional<Course> c4 = CourseRepo.findTopByOrderByDurationInMonthsDesc();
		System.out.println(c4);
		assertThat(c4.isPresent());
	}


//	3. DepartmentRepository
//	• Find by exact name.
//	@Test
//	@DisplayName("Find by exact name of Department.")
//	void findByExactDepartmentName() {
//		String deptName = "ISS";
//		Department department = dRepo.findByName(deptName);
//		System.out.println(department);
//		assertThat(department.getName().equals(deptName));
//	}

//	Find by partial name (ignoring case).
	@Test
	@DisplayName("Find by partial name (ignoring case)")
	void findByPartialNameIgnoringCase() {
		String deptName = "ISS";
		List<Department> departments = dRepo.findByNameContainingIgnoreCase(deptName);
		System.out.println(departments);
		assertThat(departments.size() > 0);
	}
	
	@Test
	@DisplayName("Find employee by partial name (ignoring case).")
	void findEmployeeByPartialNameIgnoringCase() {
		String name = "";
		List<Employee> employees = eRepo.findByNameContainingIgnoreCase(name);
		System.out.println(employees);
		assertThat(employees.size() > 0);
	}
}

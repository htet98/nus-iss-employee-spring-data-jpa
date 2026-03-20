package sg.edu.nus.spring_data_jpa.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

	@BeforeEach
	public void setUp() {
//		Employee emp1 = new Employee();
//		emp1.setName("J Bondie");
//		Employee savedEmp = empRepo.save(emp1);	
//		em.persist(savedEmp);
//		em.flush();

		// create employee 1
		Employee emp1 = new Employee();
		emp1.setName("James Bond");
		Department dept1 = new Department();
		dept1.setName("ISS");
		emp1.setDepartment(dept1);

		Project p1 = new Project();
		p1.setName("Project-X");
		p1.setStartDate(LocalDate.of(2026, 1, 1));
		p1.setEndDate(LocalDate.of(2026, 10, 31));
		p1.setDescription("this is desc");
		emp1.setProjects(Set.of(p1));

		Course course1 = new Course();
		course1.setName("Java");
		course1.setDurationInMonths(12);
		course1.setStarts(LocalDate.of(2026, 10, 10));

		Course course2 = new Course();
		course2.setName("Python 2");
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
		emp2.setName("John Bondie");
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
		course3.setName("Python 1");
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
		
		// create employee 1
		Employee emp31 = new Employee();
		emp31.setName("Paul");
		Employee savedEmp3 = eRepo.save(emp31);
		em.persist(savedEmp3);
		Long empId31 = emp31.getId();

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
//	@Test
//	@DisplayName("Find courses where the name contains a specific string, ignoring case")
//	void findCourseContainName() {
//		String searchtext = "python";
//		List<Course> courses = CourseRepo.findByNameContainingIgnoreCase(searchtext);
//		System.out.println("findCourseContainName" + " " + searchtext);
//		System.out.println(courses.size());
//		System.out.println(courses);
//		boolean resultBoolean = false;
//		for (Course c : courses) {
//
//			resultBoolean = c.getName().toLowerCase().contains(searchtext.toLowerCase());
//		}
//		assertTrue(resultBoolean);
//	}

//	Find courses starting after a specific date.
//	@Test
//	@DisplayName("Find courses starting after a specific date.")
//	void findCoursesStartAfter() {
//		System.out.println("findCoursesStartAfter");
//		LocalDate afterStartDate = LocalDate.of(2026, 3, 1);
//		boolean resultBoolean = false;
//		List<Course> courses = CourseRepo.findByStartsAfter(afterStartDate);
//		System.out.println(courses);
//		System.out.println(afterStartDate);
//		for (Course c : courses) {
//			resultBoolean = c.getStarts().isAfter(afterStartDate);
//			System.out.println(c + ":" + resultBoolean);
//		}
//		assertTrue(resultBoolean);
//	}

//	@Test
//	@DisplayName("Find courses starting after a specific date.")
//	void findCoursesStartingAfterSpecificDate() {
//		LocalDate startDate = LocalDate.of(2027, 5, 1);
//		List<Course> c3 = CourseRepo.findByStartsAfter(startDate);
//		System.out.println(c3);
//		assertThat(c3.size() > 0);
//	}

//	• Find courses by maximum duration.
	@Test
	@DisplayName("Find courses by maximum duration.")
	void findCoursesByMaximumDuration() {
		Optional<Course> c4 = CourseRepo.findTopByOrderByDurationInMonthsDesc();
		System.out.println(c4);
		assertTrue(c4.isPresent());
	}

//	2. ProjectRepository
//	• Find by exact project name.
	@Test
	@DisplayName("Find by exact project name")
	void findByExactProjectName() {
		String searchtext = "Project-X";
		Project c4 = projRepo.findByName(searchtext);
		System.out.println(c4.getName());
		System.out.println(c4);
		assertTrue(c4.getName().equals(searchtext));
	}

//	3. DepartmentRepository
//	• Find by exact name.
	@Test
	@DisplayName("Find by exact name of Department.")
	void findByExactDepartmentName() {
		String deptName = "ISS";
		Department department = dRepo.findByName(deptName);
		System.out.println(department);
		assertTrue(department.getName().equals(deptName));
	}

//	Find by partial name (ignoring case).
	@Test
	@DisplayName("Find Department by partial name (ignoring case)")
	void findByPartialNameIgnoringCase() {
		boolean resultBoolean = false;
		String searchDeptName = "ISS";
		List<Department> departments = dRepo.findByNameContainingIgnoreCase(searchDeptName);
		System.out.println(departments);
		for (Department d : departments) {
			resultBoolean = d.getName().toLowerCase().contains(searchDeptName.toLowerCase());
		}
		assertTrue(resultBoolean);
	}

	@Test
	@DisplayName("Find employee by partial name (ignoring case).")
	void findEmployeeByPartialNameIgnoringCase() {
		System.out.println("--------findEmployeeByPartialNameIgnoringCase-----------");
		boolean resultBoolean = false;
		String searchName = "bond";
		List<Employee> employees = eRepo.findByNameContainingIgnoreCase(searchName);
		for (Employee e : employees) {
			resultBoolean = e.getName().toLowerCase().contains(searchName.toLowerCase());
		}
		System.out.println(employees);
		assertTrue(resultBoolean);
	}

//	• Custom @Query: Fetch an Employee and their Projects by Employee ID (findByIdWithProjects).
//	@Query("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.projects WHERE e.id = :id")
//	Optional<Employee> findByIdWithProjects(@Param("id") Long id);

//	@Test
//	@DisplayName("Fetch an Employee and their Projects by Employee ID")
//	void fetchEmployeeAndTheirProjectsByEmployeeID() {
//		System.out.println("--------fetchEmployeeAndTheirProjectsByEmployeeID-----------");
//		boolean resultBoolean = false;
//		Long testEmpId = (long) 2;
//		Optional<Employee> employees = eRepo.findByIdWithProjects(testEmpId);
//
//		if (employees.isPresent()) {
//			Set<Project> p = emp.getProjects();
//			System.out.println(p.size());
//			resultBoolean = emp.getId() == testEmpId;
//		}
//
//		assertTrue(resultBoolean);
//	}
	
//	Custom @Query: Fetch an Employee and their Courses by Employee ID (findByIdWithCourses).
	@Test
	@DisplayName("Fetch an Employee and their Courses by Employee ID")
	void fetchAnEmployeeAndTheirCoursesByEmployeeId() {
		System.out.println("888888888888888888888888888888");
		Employee employee = eRepo.findByIdWithCourses(3L)
		        .orElseThrow(() -> new RuntimeException("Employee not found"));

		List<Course> courses = employee.getCourses();
//		System.out.println(courses.size());
		boolean result = courses.size() >= 0;
		 assertTrue(result);
	}
	

}

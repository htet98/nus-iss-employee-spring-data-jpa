package sg.edu.nus.spring_data_jpa.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 60)
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		System.out.println("employee setCourse");
		this.courses = courses;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "employee_projects", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	private Set<Project> projects = new HashSet<>();

	public void addProjects(Project p) {
		if (p != null) {
			if (projects == null) {
				projects = new HashSet<>();
				// projects = new ArrayList<>();
			}
			projects.add(p);
			p.setEmployees(Set.of(this));
		}
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<>();

	public void addCourse(Course c) {
		System.out.println(c);
		if (c != null) {
			System.out.println(c);
			if (courses == null) {
//  			courses = new HashSet<>();
				courses = new ArrayList<>();
			}
		}
		courses.add(c);
		System.out.println(c);
		c.setEmployee(this);
	}

	// Helper method to keep both sides of the relationship in sync
//	public void addCourse(Course c) {
//		System.out.println("add course");
//		System.out.println(c);
//		if (c != null) {
//			System.out.println(c);
//			if (courses == null) {
////  			courses = new HashSet<>();
//				courses = new ArrayList<>();
//			}
//			courses.add(c);
//			System.out.println(courses);
//			c.setEmployee(this);
//		}
//	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id);
	}

	public Employee() {
		super();
	}

	public Employee(String name) {
		super();
		this.name = name;
	}

	public Employee(String name, Department department) {
		super();
		this.name = name;
		this.department = department;
	}

}
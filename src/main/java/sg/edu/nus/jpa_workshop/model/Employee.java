package sg.edu.nus.jpa_workshop.model;

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

	@Column(name = "employee_name")
	private String name;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Courses> courses = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
	    name="employee_projects",
	    joinColumns = @JoinColumn(name="employee_id", referencedColumnName = "id"),
	    inverseJoinColumns = @JoinColumn(name="project_id", referencedColumnName = "id")
	)
	private Set<Project> projects = new HashSet<>();

	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="department_id", referencedColumnName = "id")
	private Department department;

	public Employee() {
		super();
	}

	public Employee(Long id, String name, List<Courses> courses, Set<Project> projects, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.courses = courses;
		this.projects = projects;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Courses> getCourses() {
		return courses;
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, courses, department, projects);
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
		return Objects.equals(name, other.name) && Objects.equals(courses, other.courses)
				&& Objects.equals(department, other.department) && Objects.equals(projects, other.projects);
	}
}

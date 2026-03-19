package sg.edu.nus.jpa_workshop.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "project_name")
	private String name;

	@Column(name= "description")
	private String description;

	@Column(name = "startDate")
	private LocalDate startDate;

	@Column(name= "endDate")
	private LocalDate endDate;

	@ManyToMany(mappedBy = "projects", fetch=FetchType.LAZY)
	private Set<Employee> employees = new HashSet<>();

	public Project() {
		super();
	}

	public Project(Long id, String name, String description, LocalDate startDate, LocalDate endDate,
			Set<Employee> employees) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employees = employees;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, employees, endDate, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(name, other.name) && Objects.equals(description, other.description)
				&& Objects.equals(employees, other.employees) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(startDate, other.startDate);
	}
}

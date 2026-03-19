package sg.edu.nus.jpa_workshop.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="courses")
public class Courses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "course_name")
	private String name;
	
	@Column(name = "durationInMonths")
	private Integer durationInMonths;
	
	@Column(name = "starts")
	private LocalDate starts;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id", referencedColumnName = "id", nullable= false)
	private Employee employee;

	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Courses(Long id, String name, Integer durationInMonths, LocalDate starts, Employee employee) {
		super();
		this.id = id;
		this.name = name;
		this.durationInMonths = durationInMonths;
		this.starts = starts;
		this.employee = employee;
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

	public Integer getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(Integer durationInMonths) {
		this.durationInMonths = durationInMonths;
	}

	public LocalDate getStarts() {
		return starts;
	}

	public void setStarts(LocalDate starts) {
		this.starts = starts;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(durationInMonths, employee, name, starts);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Courses other = (Courses) obj;
		return Objects.equals(durationInMonths, other.durationInMonths) && Objects.equals(employee, other.employee)
				&& Objects.equals(name, other.name) && Objects.equals(starts, other.starts);
	}

	
}

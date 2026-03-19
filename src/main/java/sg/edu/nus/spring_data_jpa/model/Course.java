package sg.edu.nus.spring_data_jpa.model;

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
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 60)
	private String name;

	@Column(nullable = false)
	private double durationInMonths;

	private LocalDate starts;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(double durationInMonths) {
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
		System.out.println(employee);
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", durationInMonths=" + durationInMonths
				+ ", starts=" + starts + "]";
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
		Course other = (Course) obj;
		return Objects.equals(id, other.id);
	}

	public Course() {
		super();
		System.out.println("Course construct");
	}

	public Course(String name, double durationInMonths, LocalDate starts, Employee employee) {
		super();
		this.name = name;
		this.durationInMonths = durationInMonths;
		this.starts = starts;
		this.employee = employee;
	}

	
}

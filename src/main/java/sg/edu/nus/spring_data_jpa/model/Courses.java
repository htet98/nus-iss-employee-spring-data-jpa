package sg.edu.nus.spring_data_jpa.model;

import java.time.LocalDate;

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
public class Courses {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer durationInMonths;
    private LocalDate starts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
	
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

	public int getDuration() {
		return durationInMonths;
	}

	public void setDuration(int duration) {
		this.durationInMonths = duration;
	}

	public LocalDate getStartDate() {
		return starts;
	}

	public void setStartDate(LocalDate startDate) {
		this.starts = startDate;
	}

	public int getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(int durationInMonths) {
		this.durationInMonths = durationInMonths;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Courses() {
	}

	public Courses(Long id, String name, int durationInMonths, LocalDate starts) {
		super();
		this.id = id;
		this.name = name;
		this.durationInMonths = durationInMonths;
		this.starts = starts;
	}

	@Override
	public String toString() {
		return "Courses [id=" + id + ", name=" + name + ", durationInMonths=" + durationInMonths + ", starts=" + starts
				+ ", employee=" + employee + "]";
	}

}

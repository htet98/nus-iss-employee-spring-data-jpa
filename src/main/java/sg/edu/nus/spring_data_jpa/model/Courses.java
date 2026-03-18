package sg.edu.nus.spring_data_jpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Courses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int durationInMonths;
	private LocalDate startDate;
	
	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Employee> employee = new ArrayList<>();
	
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
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public List<Employee> getBooks() {
		return employee;
	}

	public void setBooks(List<Employee> employee) {
		this.employee = employee;
	}

	public Courses() {
	}

	public Courses(Long id, String name, int durationInMonths, LocalDate startDate) {
		super();
		this.id = id;
		this.name = name;
		this.durationInMonths = durationInMonths;
		this.startDate = startDate;
	}

}

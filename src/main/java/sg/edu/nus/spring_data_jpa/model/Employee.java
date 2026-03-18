package sg.edu.nus.spring_data_jpa.model;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
		System.out.println(name);
	}
	
	

	public Employee(String name, Department department) {
		super();
		this.name = name;
		this.department = department;
	}



	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "profile_id", referencedColumnName = "id")
//    private Profile profile;  // Reference to the Profile entity
}
package hib_OTM_MTO_Bidir.dto;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dept")
public class Department {
@Id
@Column(name = "deptno")
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name = "email",unique = true)
	private String email;
	private String location;
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	public int getId() {
		return id;
	} 

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return String.format("Department [id=%s, name=%s, email=%s, location=%s]", id, name, email,
				location);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, employees, id, location, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(email, other.email) && Objects.equals(employees, other.employees) && id == other.id
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name);
	}



}

package hib_OTM_MTO_Bidir.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emp")
@Cacheable
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empid")
	private int id;

	private String name;
	@Column(unique = true)
	private long phone;
	private String job;
	private LocalDate hireDate;
	private int mgr;
	private double sal;
	private int comm;
	@ManyToOne@JoinColumn(name="deptno")
	private Department department;

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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return String.format(
				"Employee [id=%s, name=%s, phone=%s, job=%s, hireDate=%s, mgr=%s, sal=%s, comm=%s, department=%s]", id,
				name, phone, job, hireDate, mgr, sal, comm, department);
	}

	@Override
	public int hashCode() {
		return Objects.hash(comm, department, hireDate, id, job, mgr, name, phone, sal);
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
		return comm == other.comm && Objects.equals(department, other.department)
				&& Objects.equals(hireDate, other.hireDate) && id == other.id && job == other.job && mgr == other.mgr
				&& Objects.equals(name, other.name) && phone == other.phone
				&& Double.doubleToLongBits(sal) == Double.doubleToLongBits(other.sal);
	}


}

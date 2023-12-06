package hib_OTM_MTO_Bidir.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hib_OTM_MTO_Bidir.dto.Department;
import hib_OTM_MTO_Bidir.dto.Employee;
import hib_OTM_MTO_Bidir.util.IdNotFoundException;

public class EmployeeDao {

	EntityManager entityManager = Persistence.createEntityManagerFactory("sanjog").createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	DepartmentDao departmentDao = new DepartmentDao();

	public void saveEmployee(Employee employee, int deptId) {
		Department department = departmentDao.findDepartment(deptId);
		if (department != null) {
			employee.setDepartment(department);
			entityTransaction.begin();
			entityManager.persist(employee);
			entityTransaction.commit();
		} else {
			throw new IdNotFoundException("Department not found!!!");
		}
	}

	public Employee findEmployee(int id) {
		return entityManager.find(Employee.class, id);

	}

	public List<Employee> findAll() {
		return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
	}

	public List<Employee> findEmployeeByName(String name) {
		return entityManager.createQuery("select e from Employee e where e.name=?1", Employee.class)
				.setParameter(1, name).getResultList();
	}

	public List<Employee> findEmployeesBySalRange(double low, double high) {
		Query query = entityManager.createQuery("select e from Employee e where e.sal between ?1 and ?2",
				Employee.class);
		query.setParameter(1, low);
		query.setParameter(2, high);

		return query.getResultList();
	}

	public List<Employee> findEmployeesByJob(String job) {
		return entityManager.createQuery("select e from Employee e where e.job=?1", Employee.class).setParameter(1, job)
				.getResultList();
	}

	public void deleteEmployee(int id) {
		Employee employee = findEmployee(id);
		if (employee != null) {
			entityTransaction.begin();
			entityManager.remove(employee);
			entityTransaction.commit();
			System.out.println("Employee Deleted");
		} else {
			throw new IdNotFoundException("Employee Not Found To Delete");
		}
	}

	public void updateEmployee(int id, Employee employee) {
		Employee dbEmployee = findEmployee(id);
		if (dbEmployee != null) {
			employee.setDepartment(dbEmployee.getDepartment());
			employee.setId(id);
			entityTransaction.begin();
			entityManager.merge(employee);
			entityTransaction.commit();
			System.out.println("Update of Employee is successful");
		} else {
			throw new IdNotFoundException("Update Failed  as id not Found");
		}

	}

	public void changeDepartment(int empId, int deptId) {
		Employee dbEmployee = findEmployee(empId);
		Department dbDepartment = departmentDao.findDepartment(deptId);
		if (dbEmployee != null && dbDepartment != null) {
			dbEmployee.setDepartment(dbDepartment);
			entityTransaction.begin();
			entityManager.merge(dbEmployee);
			entityTransaction.commit();
			System.out.println("Update of Employee is successful");
		} else if (dbEmployee == null) {
			throw new IdNotFoundException("Update Failed  as Employee id not Found");
		} else {
			throw new IdNotFoundException("Update Failed as Department Id Not Found");
		}

	}
	

}

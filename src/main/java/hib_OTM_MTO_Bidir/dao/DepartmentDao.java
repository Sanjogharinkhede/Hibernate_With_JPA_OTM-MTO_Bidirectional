package hib_OTM_MTO_Bidir.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hib_OTM_MTO_Bidir.dto.Department;
import hib_OTM_MTO_Bidir.util.IdNotFoundException;

public class DepartmentDao {

	EntityManager entityManager=Persistence.createEntityManagerFactory("sanjog").createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	
	public void saveDepartment(Department department) {
		entityTransaction.begin();
		entityManager.persist(department);
		entityTransaction.commit();
	}

	public Department findDepartment(int id) {
		return entityManager.find(Department.class,id);
	}
	public List<Department> findAll() {
		return entityManager.createQuery("select d from Department d", Department.class).getResultList();
	}
	
	public void updateDepartment(int id, Department department) {
		Department dbDepartment = findDepartment(id);
		if (dbDepartment != null) {
			department.setId(id);
			entityTransaction.begin();
			entityManager.merge(department);
			entityTransaction.commit();
			System.out.println("Update of Department is successful");
		} else {
			throw new IdNotFoundException("Update Failed  as id not Found");
		}

	}
	public void deleteDepartment(int id) {
		Department department = findDepartment(id);
		if (department != null) {
			entityTransaction.begin();
			entityManager.remove(department);
			entityTransaction.commit();
			System.out.println("Department Deleted");
		} else {
			throw new IdNotFoundException("Department Not Found To Delete");
		}
	}
	
}

package hib_OTM_MTO_Bidir.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import hib_OTM_MTO_Bidir.dao.DepartmentDao;
import hib_OTM_MTO_Bidir.dao.EmployeeDao;
import hib_OTM_MTO_Bidir.dto.Department;
import hib_OTM_MTO_Bidir.dto.Employee;
import hib_OTM_MTO_Bidir.util.IncorrectInputException;
import hib_OTM_MTO_Bidir.util.WrongEmailException;
import hib_OTM_MTO_Bidir.util.WrongPhoneNumberException;

public class Controller {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Enter 1.Department Admin \n2.Employee Dept");
		switch (Integer.parseInt(scanner.nextLine())) {
		case 1: {
			deptController();
			break;
		}
		case 2: {
			empController();
			break;
		}
		default: {
			throw new IncorrectInputException();
		}

		}

	}

	public static void deptController() {
		DepartmentDao dao = new DepartmentDao();
		loop: while (true) {
			System.out
					.println("1.save department \n2.find by id \n3.delete department \n4.update department\n5.Log Out");
			switch (Integer.parseInt(scanner.nextLine())) {
			case 1: {
				Department department = new Department();
				System.out.println("Enter the name of department");
				department.setName(scanner.nextLine());
				System.out.println("Enter the email id of department");
				String email = scanner.nextLine();
				if (email.matches("[a-zA-Z0-9?.$]+@[a-zA-Z]+.[a-z]{2,3}")) {
					department.setEmail(email);
				} else {
					throw new WrongEmailException();
				}
				System.out.println("Enter the location of department");
				department.setLocation(scanner.nextLine());

				dao.saveDepartment(department);
				break;
			}
			case 2: {
				System.out.println("Enter the id of the department");
				Department department = dao.findDepartment(Integer.parseInt(scanner.nextLine()));
				if (department != null) {
					System.out.println(department);
				} else {
					System.out.println("No department for given Id");
				}
				break;
			}
			case 3: {
				System.out.println("Enter the Id to delete:");
				dao.deleteDepartment(Integer.parseInt(scanner.nextLine()));
				break;

			}
			case 4: {
				Department department = new Department();
				System.out.println("Enter the name of department");
				department.setName(scanner.nextLine());
				System.out.println("Enter the email id of department");
				String email = scanner.nextLine();
				if (email.matches("[a-zA-Z0-9?.$]+@[a-zA-Z]+.[a-z]{2,3}")) {
					department.setEmail(email);
				} else {
					throw new WrongEmailException();
				}
				System.out.println("Enter the location of department");
				department.setLocation(scanner.nextLine());

				System.out.println("Enter the Id of department");
				dao.updateDepartment(Integer.parseInt(scanner.nextLine()), department);
				break;
			}
			case 5: {
				System.out.println("Logging Out!!!!!!!!");
				break loop;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + Integer.parseInt(scanner.nextLine()));
			}
		}

	}

	public static void empController() {
		EmployeeDao employeeDao = new EmployeeDao();
		loop: while (true) {
			System.out.println(
					"1.Save The Employee \n 2.Find Employee By name " + "\n3.Find Employee By id \n4.find All \n"
							+ "5.Find Employee By Job \n6.find Employee by sal Range" + "\n7.Delete Employee "
							+ "\n8.update The Employee \n 9.change department of the Employee " + "\n10. Log out");

			switch (Integer.parseInt(scanner.nextLine())) {
			case 1: {
				Employee employee = new Employee();

				System.out.println("************ Enter the Employee Details  ***********");
				System.out.println("Enter Employee name: ");
				employee.setName(scanner.nextLine());
				System.out.println("Enter Employee phone: ");
				String phone = scanner.nextLine();

				if (phone.matches("[6-9]{1}[0-9]{9}")) {

					employee.setPhone(Long.parseLong(phone));
				} else {
					throw new WrongPhoneNumberException();
				}

				System.out.println("Enter Employee job Role: ");
				employee.setJob(scanner.nextLine());

				System.out.println("Enter Employee Manager Id: ");
				employee.setMgr(Integer.parseInt(scanner.nextLine()));

				System.out.println("Enter Employee Salary: ");
				employee.setSal(Double.parseDouble(scanner.nextLine()));

				System.out.println("Enter Employee Commision: ");
				employee.setComm(Integer.parseInt(scanner.nextLine()));

				System.out.println("Enter the Hiredate of the Employee \n****Format:->> yyyy-mm-dd****");
				employee.setHireDate(LocalDate.parse(scanner.nextLine()));

				System.out.println("Enter the Department Number/id ");
				System.out.println("******");
				new DepartmentDao().findAll().forEach(s->System.out.println(s.getId()));
				System.out.println("******");

				employeeDao.saveEmployee(employee, Integer.parseInt(scanner.nextLine()));
				break;
			}
			case 2: {
				System.out.print("Enter the name of the Employee :");
				List<Employee> employees = employeeDao.findEmployeeByName(scanner.nextLine());
				if (employees.isEmpty()) {
					System.out.println("Employees Not Found");
				} else {
					employees.forEach(s -> System.out.println(s));
				}
				break;

			}
			case 3: {
				System.out.println("Enter The Id Of Employee : ");
				Employee employee = employeeDao.findEmployee(Integer.parseInt(scanner.nextLine()));
				if(employee!=null) {
					System.out.println(employee);
				}else {
					System.out.println("Employee not Found with given ID");
				}
				break;
			}
			case 4: {
				System.out.println("Here's The Full List");
				employeeDao.findAll().forEach(s -> System.out.println(s));
				break;
			}
			case 5: {
				System.out.println("Enter the Job role:");
				List<Employee> employees = employeeDao.findEmployeesByJob(scanner.nextLine());
				if (employees.isEmpty()) {
					System.out.println("Employees Not Found");
				} else {
					employees.forEach(s -> System.out.println(s));
				}
				break;
			}
			case 6: {
				System.out.println(
						"Enter the lower range value & Higher range value separeted by line \n*****do not give spaces******");
				List<Employee> employees = employeeDao.findEmployeesBySalRange(Double.parseDouble(scanner.nextLine()),
						Double.parseDouble(scanner.nextLine()));
				if (employees.isEmpty()) {
					System.out.println("Employees Not Found");
				} else {
					employees.forEach(s -> System.out.println(s));
				}
				break;

			}
			case 7: {
				System.out.println("Enter the Id to delete:");
				employeeDao.deleteEmployee(Integer.parseInt(scanner.nextLine()));
				break;
			}
			case 8: {
				Employee employee = new Employee();

				System.out.println("************ Enter the Employee Details  ***********");
				System.out.println("Enter Employee name: ");
				employee.setName(scanner.nextLine());
				System.out.println("Enter Employee phone: ");
				String phone = scanner.nextLine();

				if (phone.matches("[6-9]{1}[0-9]{9}")) {

					employee.setPhone(Long.parseLong(phone));
				} else {
					throw new WrongPhoneNumberException();
				}

				System.out.println("Enter Employee job Role: ");
				employee.setJob(scanner.nextLine());

				System.out.println("Enter Employee Manager Id: ");
				employee.setMgr(Integer.parseInt(scanner.nextLine()));

				System.out.println("Enter Employee Salary: ");
				employee.setSal(Double.parseDouble(scanner.nextLine()));

				System.out.println("Enter Employee Commision: ");
				employee.setComm(Integer.parseInt(scanner.nextLine()));

				System.out.println("Enter the Hiredate of the Employee");
				employee.setHireDate(LocalDate.parse(scanner.nextLine()));

				System.out.println("Enter The employee id");
				employeeDao.updateEmployee(Integer.parseInt(scanner.nextLine()), employee);
				break;
			}
			case 9: {
				System.out.println("Enter the emp id and new dept Id \n*****do not give spaces******");
				employeeDao.changeDepartment(Integer.parseInt(scanner.nextLine()),
						Integer.parseInt(scanner.nextLine()));
				break;
			}
			case 10: {
				System.out.println("Closing....!!!! \n Thank You");
				break loop;
				
			}
			default: {
				throw new IncorrectInputException();
			}
			}
		}
	}

}

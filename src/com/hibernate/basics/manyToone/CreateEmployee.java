package com.hibernate.basics.manyToone;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.Department;
import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.SalaryAccount;

public class CreateEmployee {

	public static void main(String[] args) {
		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.oneToone.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class)
				.addAnnotatedClass(Department.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		SalaryAccount account = new SalaryAccount("IDFC", "AXIS001", "KATRAJ, PUNE");

		EmployeeOneToOne employee1 = new EmployeeOneToOne("last", "mail", "last@gmail.com");

		employee1.setSalaryAccount(account);

		Department department = new Department("Logistic", "This department manages Logistic Resources");
		Department department1 = new Department("Fin", "This department manages Finance");


		employee1.add(department);
		employee1.add(department1);
		employee1.setDepartmentList(new ArrayList<Department>() {{
			add(department);
			add(department1);
			}}
		);

		try {

			// Starting the Transaction
			session.beginTransaction();

			// Persisting the employee object in db. This will save both parent and child object
			// As cascade is all salary account object will also be saved
			session.persist(employee1);

			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}
	}

}

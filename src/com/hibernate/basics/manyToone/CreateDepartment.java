package com.hibernate.basics.manyToone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.Department;
import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.SalaryAccount;

public class CreateDepartment {

	public static void main(String[] args) {
		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class)
				.addAnnotatedClass(Department.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		Department department = new Department("H.R.", "This department manages Human Resources");
		Department department1 = new Department("Fin", "This department manages Finance");

		try {

			// Starting the Transaction
			session.beginTransaction();

			EmployeeOneToOne employeeOneToOne = session.get(EmployeeOneToOne.class, 5);
			employeeOneToOne.add(department1);
			employeeOneToOne.add(department);

			// Saving the employee object in db
			// As cascade is all salary account object will also be saved
			session.save(department);
			session.save(department1);

			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}
	}

}

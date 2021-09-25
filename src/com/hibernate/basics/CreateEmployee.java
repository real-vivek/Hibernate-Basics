package com.hibernate.basics;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.Employee;

public class CreateEmployee {

	public static void main(String[] args) {

		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		Employee employee1 = new Employee("First", "Last", "first@gmail.com");
		try {

			// Starting the Transaction
			session.beginTransaction();

			// Saving the employee object in db
			session.save(employee1);

			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}

	}
}

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

		try {

			// Begin Transaction
			session.beginTransaction();

			// Retrieving Employee object having id 1
			// For session.get to work we need default constructor
			// If employee with given id is not present then null is returned
			Employee retrievedEmployee = session.get(Employee.class, 1);
			
			System.out.println("Here is the employee which is retrieved: "+retrievedEmployee);

			// Saving the employee object in db
			session.getTransaction().commit();
		} finally {
			sessionFactory.close();
		}

	}
}

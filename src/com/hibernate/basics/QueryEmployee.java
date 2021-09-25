package com.hibernate.basics;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.Employee;

public class QueryEmployee {
	
	public static void main(String[] args) {

		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		try {

			// Starting the Transaction
			session.beginTransaction();

			// Querying Employee object having gmail as their email addresses
			// If no such entry is found then empty list is returned
			List<?> resultList = session.createQuery("from Employee e where e.email like '%@gmail.com'").getResultList();

			System.out.println("Here is/are the employee/s which have last name as 'last': " + resultList);

			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}
	}
	
}

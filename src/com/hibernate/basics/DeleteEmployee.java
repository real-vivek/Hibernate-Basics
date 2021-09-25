package com.hibernate.basics;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.Employee;

public class DeleteEmployee {

	public static void main(String[] args) {

		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();

		try {

			// Creating session object
			Session session = sessionFactory.getCurrentSession();

			// Starting the Transaction
			session.beginTransaction();

			// Retrieving Employee object having id 1
			Employee retrievedEmployee = session.get(Employee.class, 1);

			// Deleting retrieved employee object
			// If retrievedEmployee is null then IllegalArgumentException is thrown
			session.delete(retrievedEmployee);

			System.out.println("Here is the employee which is deleted: " + retrievedEmployee);

			// Committing the transaction
			session.getTransaction().commit();

			// Creating new session
			Session newSession = sessionFactory.getCurrentSession();

			// Starting the Transaction
			newSession.beginTransaction();

			// Deleting email using hql
			// If no record is found then no exception is thrown
			int rowsUpdated = newSession.createQuery("delete from Employee where id=2").executeUpdate();

			System.out.println("Number of rows affeted:" + rowsUpdated);

			// Committing the transaction
			newSession.getTransaction().commit();

		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}

	}
}

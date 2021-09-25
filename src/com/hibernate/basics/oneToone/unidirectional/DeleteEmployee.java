package com.hibernate.basics.oneToone.unidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.SalaryAccount;

public class DeleteEmployee {

	public static void main(String[] args) {

		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.oneToone.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();
		
		try {

			// Starting the Transaction
			session.beginTransaction();

			// Retrieving Employee object having id 1
			EmployeeOneToOne retrievedEmployee = session.get(EmployeeOneToOne.class, 187);

			// Deleting retrieved employee object
			// If retrievedEmployee is null then IllegalArgumentException is thrown
			// As cascade is all salary account corresponding to id employee having id 1 will also be deleted
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
			// As cascade is all salary account corresponding to id employee having id 2 will also be deleted
			int rowsUpdated = newSession.createQuery("delete from EmployeeOneToOne where id=2").executeUpdate();

			System.out.println("Number of rows affeted:" + rowsUpdated);

			// Committing the transaction
			newSession.getTransaction().commit();

		} catch(Exception e){
			e.printStackTrace();
		}finally {
			// CLosing session object
			session.close();
			
			// Closing the session factory object
			sessionFactory.close();
		}

	}
}

package com.hibernate.basics.oneToone.unidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.SalaryAccount;

public class DeleteSalaryAccountOnly {

	public static void main(String[] args) {
		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.oneToone.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		try {

			// Starting the Transaction
			session.beginTransaction();

			// Retrieving SalaryAccount object having id 11
			SalaryAccount retrievedSalaryAccount = session.get(SalaryAccount.class, 11);

			// Deleting retrieved SalaryAccount object
			// If retrievedSalaryAccount is null then IllegalArgumentException is thrown

			// Before deleting retrievedSalaryAccount we have to break link between Employee
			// and SalaryAccount
			// Otherwise the id in employee table will point to no object in Salary Account
			retrievedSalaryAccount.getEmployeeOneToOne().setSalaryAccount(null);
			session.delete(retrievedSalaryAccount);

			System.out.println("Here is the Salary Account which is deleted: " + retrievedSalaryAccount);

			// Committing the transaction
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// CLosing session object
			session.close();

			// Closing the session factory object
			sessionFactory.close();
		}
	}
}

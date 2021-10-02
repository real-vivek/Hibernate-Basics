package com.hibernate.basics.oneToone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.SalaryAccount;

public class RetrieveEmployee {

	public static void main(String[] args) {

		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		try {

			// Starting the Transaction
			session.beginTransaction();

			// Retrieving SalaryAccount object having id 1
			// For session.get to work we need default constructor
			// If employee with given id is not present then null is returned
			SalaryAccount retrievedSalaryAccount = session.get(SalaryAccount.class, 1);

			// If we update salaryAcc toString to print employeeOneToOne we get StackOverflowError because both objects have each others object in toString
			System.out.println("Here is the salary account which is retrieved: " + retrievedSalaryAccount);

			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}

	}
}

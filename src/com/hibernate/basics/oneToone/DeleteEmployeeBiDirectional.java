package com.hibernate.basics.oneToone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.SalaryAccount;

public class DeleteEmployeeBiDirectional {

	public static void main(String[] args) {

		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.oneToone.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		try {

			// Starting the Transaction
			session.beginTransaction();

			// Retrieving SalaryAccount object having id 10
			SalaryAccount retrievedSalaryAccount = session.get(SalaryAccount.class, 10);

			// Deleting retrieved SalaryAccount object
			// If retrievedSalaryAccount is null then IllegalArgumentException is thrown
			// As cascade is not all employee corresponding to id salary account having id 10
			// will not be deleted
			
			// Before deleting retrievedSalaryAccount we have to break link between Employee and SalaryAccount
			// Otherwise the id in employee table will point to no object in Salary Account
			retrievedSalaryAccount.getEmployeeOneToOne().setSalaryAccount(null);
			
			session.delete(retrievedSalaryAccount);

			System.out.println("Here is the Salary Account which is deleted: " + retrievedSalaryAccount);

			// Committing the transaction
			session.getTransaction().commit();

			// Creating new session
			Session newSession = sessionFactory.getCurrentSession();

			// Starting the Transaction
			newSession.beginTransaction();

			// Deleting Salary Account using hql
			// If no record is found then no exception is thrown
			// This will cause ConstraintViolation Error as below query will only delete
			// Salary Account query but not associated Employee object
			// Thus hql query will only delete given object not associated object i.e. it does not cascade delete
			
			// int rowsUpdated = newSession.createQuery("delete from SalaryAccount where id=7").executeUpdate();


			// Committing the transaction
			newSession.getTransaction().commit();

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

package com.hibernate.basics.oneToone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.SalaryAccount;

public class CreateEmployee {

	public static void main(String[] args) {
		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		SalaryAccount account = new SalaryAccount("HDFC", "HDFC001", "DHANKAWADI, PUNE");

		EmployeeOneToOne employee1 = new EmployeeOneToOne("First", "Last", "first@gmail.com");

		employee1.setSalaryAccount(account);

		try {

			// Starting the Transaction
			session.beginTransaction();

			// Saving the employee object in db
			// As cascade is all salary account object will also be saved
			session.save(employee1);

			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}
	}

}

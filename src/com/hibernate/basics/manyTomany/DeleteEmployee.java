package com.hibernate.basics.manyTomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.Department;
import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.Project;
import com.hibernate.basics.entity.Rating;
import com.hibernate.basics.entity.SalaryAccount;

public class DeleteEmployee {

	public static void main(String[] args) {

		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class)
				.addAnnotatedClass(Department.class).addAnnotatedClass(Project.class).addAnnotatedClass(Rating.class).buildSessionFactory();
		
		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		try {

			// Starting the Transaction
			session.beginTransaction();

			EmployeeOneToOne retrievedEmployee = session.get(EmployeeOneToOne.class, 18);

			// projects will not be deleted however entry in join table will be deleted
			session.delete(retrievedEmployee);

			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}

	}
}

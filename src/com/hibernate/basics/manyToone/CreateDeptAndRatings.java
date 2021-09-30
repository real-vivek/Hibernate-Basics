package com.hibernate.basics.manyToone;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.Department;
import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.Rating;
import com.hibernate.basics.entity.SalaryAccount;

public class CreateDeptAndRatings {

	public static void main(String[] args) {
		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.oneToone.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class).addAnnotatedClass(Department.class).addAnnotatedClass(Rating.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		Department department = new Department("Logistic", "This department manages Supply and Demand");

		department.setRatings(new ArrayList<Rating>() {{
			add(new Rating(5));
			add(new Rating(2));
		}});
		
		try {
			// Starting the Transaction
			session.beginTransaction();

			EmployeeOneToOne employeeOneToOne = session.get(EmployeeOneToOne.class, 5);
			employeeOneToOne.add(department);
			
			session.save(department);

			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}
	}
}

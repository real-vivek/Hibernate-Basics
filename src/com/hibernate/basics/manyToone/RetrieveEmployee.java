package com.hibernate.basics.manyToone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.Department;
import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.SalaryAccount;

public class RetrieveEmployee {

	public static void main(String[] args) {

		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.oneToone.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class)
				.addAnnotatedClass(Department.class).buildSessionFactory();
		
		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		try {

			// Starting the Transaction
			session.beginTransaction();

			EmployeeOneToOne retrievedEmployee = session.get(EmployeeOneToOne.class, 5);

			System.out.println("Here is the employee retrieved: " + retrievedEmployee);

			// Hibernate doesn't run any query 2nd time because fethType is EAGER and it has already got the Department List data
			System.out.println("Here is the employee retrieved: " + retrievedEmployee.getDepartmentList());
			
			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}

	}
}

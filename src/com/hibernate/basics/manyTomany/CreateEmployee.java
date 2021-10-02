package com.hibernate.basics.manyTomany;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basics.entity.Department;
import com.hibernate.basics.entity.EmployeeOneToOne;
import com.hibernate.basics.entity.Project;
import com.hibernate.basics.entity.Rating;
import com.hibernate.basics.entity.SalaryAccount;

public class CreateEmployee {

	public static void main(String[] args) {
		// Creating session factory object
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmployeeOneToOne.class).addAnnotatedClass(SalaryAccount.class)
				.addAnnotatedClass(Department.class).addAnnotatedClass(Project.class).addAnnotatedClass(Rating.class).buildSessionFactory();

		// Creating session object
		Session session = sessionFactory.getCurrentSession();

		SalaryAccount account = new SalaryAccount("IDFC", "AXIS001", "KATRAJ, PUNE");

		EmployeeOneToOne employee1 = new EmployeeOneToOne("last", "mail", "last@gmail.com");

		employee1.setSalaryAccount(account);


		try {

			// Starting the Transaction
			session.beginTransaction();
			
			// Saving the employee object first so that we can associate projects to it
			session.save(employee1);
			
			Project project1 = new Project("Salesforce", "US");
			Project project2 = new Project("Cisco", "UK");
			List<Project> listProject=new ArrayList<Project>() {
				{
					add(project1);
					add(project2);
				}
			};
			
			// Associating projects to employee object
			employee1.setProjects(listProject);
			
			//Saving project objects
			session.save(project1);
			session.save(project2);
			

			// Committing the transaction
			session.getTransaction().commit();
		} finally {
			// Closing the session factory object
			sessionFactory.close();
		}
	}

}

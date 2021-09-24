package com.hibernate.basics;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		// Instead of "hb_employee_tracker" in below URL you can give your db name
		String jdbcURL="jdbc:mysql://localhost:3306/hb_employee_tracker";
		try {
			// Instead of "hbemployee", "hbemployee" you have to use the userName and password that you have created
			Connection connection = DriverManager.getConnection(jdbcURL, "hbemployee", "hbemployee");
			System.out.println("Successfully connected!!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

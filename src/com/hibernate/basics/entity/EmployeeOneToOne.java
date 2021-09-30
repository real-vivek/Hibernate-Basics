package com.hibernate.basics.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeOneToOne {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "sal_acc_id")
	private SalaryAccount salaryAccount;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "employee", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<Department> departmentList;

	public EmployeeOneToOne() {
	}

	public EmployeeOneToOne(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SalaryAccount getSalaryAccount() {
		return salaryAccount;
	}

	public void setSalaryAccount(SalaryAccount salaryAccount) {
		this.salaryAccount = salaryAccount;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentList, email, firstName, id, lastName, salaryAccount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeOneToOne other = (EmployeeOneToOne) obj;
		return Objects.equals(departmentList, other.departmentList) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(salaryAccount, other.salaryAccount);
	}

	@Override
	public String toString() {
		return "EmployeeOneToOne [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}

	public void add(Department department) {
		if (departmentList == null) {
			departmentList = new ArrayList<Department>();
		}
		departmentList.add(department);
		department.setEmployee(this);
	}

}

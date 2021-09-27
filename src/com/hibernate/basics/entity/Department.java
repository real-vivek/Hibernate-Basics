package com.hibernate.basics.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "department_desc")
	private String departmentDesc;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "employee_id")
	private EmployeeOneToOne employee;

	public Department() {
	}

	public Department(String departmentName, String departmentDesc) {
		this.departmentName = departmentName;
		this.departmentDesc = departmentDesc;
	}

	public Integer getId() {
		return id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentDesc() {
		return departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	public EmployeeOneToOne getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeOneToOne employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentDesc, departmentName, employee, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(departmentDesc, other.departmentDesc)
				&& Objects.equals(departmentName, other.departmentName) && Objects.equals(employee, other.employee)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + ", departmentDesc=" + departmentDesc
				+ ", employee=" + employee + "]";
	}

}

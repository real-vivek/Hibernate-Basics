package com.hibernate.basics.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "project_name")
	private String name;

	@Column(name = "project_location")
	private String porjectLocation;
	
	public Project() {
	}

	public Project(String name, String porjectLocation) {
		this.name = name;
		this.porjectLocation = porjectLocation;
	}

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<EmployeeOneToOne> employeeList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPorjectLocation() {
		return porjectLocation;
	}

	public void setPorjectLocation(String porjectLocation) {
		this.porjectLocation = porjectLocation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, porjectLocation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(porjectLocation, other.porjectLocation);
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", porjectLocation=" + porjectLocation + "]";
	}

}

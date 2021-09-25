package com.hibernate.basics.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sal_acc")
public class SalaryAccount {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accId;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_acc_no")
	private String bankAccNo;

	@Column(name = "bank_branch")
	private String bankBranch;

	public SalaryAccount() {
	}

	public SalaryAccount(String bankName, String bankAccNo, String bankBranch) {
		this.bankName = bankName;
		this.bankAccNo = bankAccNo;
		this.bankBranch = bankBranch;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAcc() {
		return bankAccNo;
	}

	public void setBankAcc(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accId, bankAccNo, bankBranch, bankName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalaryAccount other = (SalaryAccount) obj;
		return Objects.equals(accId, other.accId) && Objects.equals(bankAccNo, other.bankAccNo)
				&& Objects.equals(bankBranch, other.bankBranch) && Objects.equals(bankName, other.bankName);
	}

	@Override
	public String toString() {
		return "SalaryAccount [accId=" + accId + ", bankName=" + bankName + ", bankAccNo=" + bankAccNo + ", bankBranch="
				+ bankBranch + "]";
	}

}

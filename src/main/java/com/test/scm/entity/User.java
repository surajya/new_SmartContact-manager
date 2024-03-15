package com.test.scm.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uId;
	private String uName;
	
	//@Column(unique=true)
	private String uEmail;
	
	//@Column(unique=true)
	private String uPassword;
	private String uRole;
	private boolean uEnable;
	
	@Column(length = 5000)
	private String uDiscription;
	private String uImageUrl;
	
	@OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY, mappedBy = "user")
	List<Contact> contacts=new ArrayList<>();
	
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getuRole() {
		return uRole;
	}
	public void setuRole(String uRole) {
		this.uRole = uRole;
	}
	public boolean isuEnable() {
		return uEnable;
	}
	public void setuEnable(boolean uEnable) {
		this.uEnable = uEnable;
	}
	public String getuDiscription() {
		return uDiscription;
	}
	public void setuDiscription(String uDiscription) {
		this.uDiscription = uDiscription;
	}
	public String getuImageUrl() {
		return uImageUrl;
	}
	public void setuImageUrl(String uImageUrl) {
		this.uImageUrl = uImageUrl;
	}
	
	
	
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String uName, String uEmail, String uPassword, String uRole, boolean uEnable, String uDiscription,
			String uImageUrl) {
		super();
		this.uName = uName;
		this.uEmail = uEmail;
		this.uPassword = uPassword;
		this.uRole = uRole;
		this.uEnable = uEnable;
		this.uDiscription = uDiscription;
		this.uImageUrl = uImageUrl;
	}
	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uEmail=" + uEmail + ", uPassword=" + uPassword + ", uRole="
				+ uRole + ", uEnable=" + uEnable + ", uDiscription=" + uDiscription + ", uImageUrl=" + uImageUrl + "]";
	}
	
	
}

package com.test.scm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	private String cName;
	private String cNickName;
	
	@Column(unique=true)
	private String cEmail;
	
	private int cPhone;
	private String cWork;
	private boolean cEnable;
	
	@Column(length = 5000)
	private String cDiscription;
	private String cImageUrl;
	
	@ManyToOne
	private User user;
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcNickName() {
		return cNickName;
	}
	public void setcNickName(String cNickName) {
		this.cNickName = cNickName;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public int getcPhone() {
		return cPhone;
	}
	public void setcPhone(int cPhone) {
		this.cPhone = cPhone;
	}
	public String getcWork() {
		return cWork;
	}
	public void setcWork(String cWork) {
		this.cWork = cWork;
	}
	public boolean iscEnable() {
		return cEnable;
	}
	public void setcEnable(boolean cEnable) {
		this.cEnable = cEnable;
	}
	public String getcDiscription() {
		return cDiscription;
	}
	public void setcDiscription(String cDiscription) {
		this.cDiscription = cDiscription;
	}
	public String getcImageUrl() {
		return cImageUrl;
	}
	public void setcImageUrl(String cImageUrl) {
		this.cImageUrl = cImageUrl;
	}
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Contact(int cId, String cName, String cNickName, String cEmail, int cPhone, String cWork, boolean cEnable,
			String cDiscription, String cImageUrl, User user) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cNickName = cNickName;
		this.cEmail = cEmail;
		this.cPhone = cPhone;
		this.cWork = cWork;
		this.cEnable = cEnable;
		this.cDiscription = cDiscription;
		this.cImageUrl = cImageUrl;
		this.user = user;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Contact [cId=" + cId + ", cName=" + cName + ", cNickName=" + cNickName + ", cEmail=" + cEmail
				+ ", cPhone=" + cPhone + ", cWork=" + cWork + ", cEnable=" + cEnable + ", cDiscription=" + cDiscription
				+ ", cImageUrl=" + cImageUrl + ", user=" + user + "]";
	}
	
	@Override
	public boolean equals(Object object) {
		return this.cId ==((Contact)object).getcId();
	}
	
	
}

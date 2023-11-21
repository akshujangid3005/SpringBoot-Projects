package com.digital.entitys;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cid;
	
	private String name;
	
	private String nickname;
	
	private String work;
	
	private String email;
	
	private String phoneNo;
	
	private String image;
	
	
	
	@ManyToOne
	private User user;

	public Contact(int cid, String name, String nickname, String work, String email, String phoneNo, String image
			) {
		super();
		this.cid = cid;
		this.name = name;
		this.nickname = nickname;
		this.work = work;
		this.email = email;
		this.phoneNo = phoneNo;
		this.image = image;
		
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.cid==((Contact)obj).getCid();
	}
	/*
	 * @Override public String toString() { return "Contact [cid=" + cid + ", name="
	 * + name + ", nickname=" + nickname + ", work=" + work + ", email=" + email +
	 * ", phoneNo=" + phoneNo + ", image=" + image + ", user=" + user + "]"; }
	 */
	
	
	
	
	
}

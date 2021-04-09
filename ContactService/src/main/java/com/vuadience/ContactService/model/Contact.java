package com.vuadience.ContactService.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;


@Entity
@Table(name="contacts")
public class Contact implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactId;
	
	@Column
	@NotNull
	@Size(min = 6, message = "Full Name must be atleast 6 chracters")
	private String fullName;
	
	@Column
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dateOfBirth;
	
	@Embedded
	private Address address;
	
	public Contact(Long contactId, @Size(min = 6, message = "Full Name must be atleast 6 chracters") String fullName,
			Date dateOfBirth, Address address) {
		super();
		this.contactId = contactId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

	public Contact() {}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth
				+ ", address=" + address + "]";
	}
	
}

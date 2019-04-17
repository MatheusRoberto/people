package com.matheus.people.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "people")
@NamedQueries({
	@NamedQuery(name = "People.findAll", query = "SELECT p FROM People p"),
	@NamedQuery(name = "People.findByName", query = "SELECT p FROM People p WHERE p.name like :name"),
	@NamedQuery(name = "People.findByEmail", query = "SELECT p FROM People p WHERE p.email like :email"),
	@NamedQuery(name = "People.findByNameAndEmail", query = "SELECT p FROM People p WHERE p.name like :name AND p.email like :email")
})
public class People implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idpeople")
	private Integer idPeople;
	
	@Column(name = "name")
	private String name;
	
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "telephone")
	private String telephone;

	public Integer getIdPeople() {
		return idPeople;
	}

	public void setIdPeople(Integer idPeople) {
		this.idPeople = idPeople;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPeople != null ? idPeople.hashCode() : 0);
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof People))
			return false;
		
		People other = (People) obj;
		return !((this.idPeople == null && other.idPeople != null)
				|| (this.idPeople != null && !this.idPeople.equals(other.idPeople)));
	}
	
}

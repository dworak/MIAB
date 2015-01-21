package co.airdo.spring.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import co.airdo.spring.validation.ValidEmail;

public class Mechanic {
	private int id;
	
	@Size(min=5, max=100, message="Name must be between 5 and 100 characters.")
	private String name;
	
	@NotNull
	@Pattern(regexp=".*\\@.*\\..*", message="This does not appear to be a valid email address")
	@ValidEmail(min=6, message="This email address is not valid.")
	private String email;
	
	public Mechanic() {	
	}

	public Mechanic(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public Mechanic(int id, String name, String email, String text) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Mechanic [id=" + id + ", name=" + name + ", email=" + email
				+ "]";
	}
}

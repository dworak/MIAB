package co.airdo.spring.dao;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import co.airdo.spring.validation.ValidEmail;

public class Offer {
	private int id;
	
	// Walidacja osoby
	@Size(min=5, max=100, message="Name must be between 5 and 100 characters.")
	private String name;
	
	// Walidacja emaila
	@NotNull
	@Pattern(regexp=".*\\@.*\\..*", message="This does not appear to be a valid email address")
	@ValidEmail(min=6, message="This email address is not valid.")
	private String email;
	
	// Walidacja notatki do zlecenia
	@Size(min=5, max=100, message="Text must be between 20 and 255 characters.")
	private String text;
	
	// Walidacja, id przekazywane w formularzu
	@NotNull
	private int mechanicId;
	
	// Powiazanie ofert z konkretnymi mechanikami
	private Mechanic mechanic;
	
	// Terminy wykonania zlecen
	private Date dueDate;
	private Date createDate;
	
	// Wartosc przekazywana z widoku
	private String dueDateString;
	
	public String getDueDateString() {
		return dueDateString;
	}

	public void setDueDateString(String dueDateString) {
		this.dueDateString = dueDateString;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public Offer() {
	}

	public Offer(String name, String email, String text, int mechanicId) {
		this.name = name;
		this.email = email;
		this.text = text;
		this.mechanicId = mechanicId;
	}
	
	public Offer(int id, String name, String email, String text, int mechanicId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
		this.mechanicId = mechanicId;
	}

	public int getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(int mechanicId) {
		this.mechanicId = mechanicId;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", email=" + email
				+ ", text=" + text + ", mechanic=" + mechanicId + ", creationDate= " + createDate + ", dueDate= " + dueDate +"]";
	}

}

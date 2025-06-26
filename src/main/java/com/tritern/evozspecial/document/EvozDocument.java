package com.tritern.evozspecial.document;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "evozspecial")
public class EvozDocument {

	@Id
	private String id;

	@Field(name = "Emailid")
	private String email;

	@Field(name = "Image")
	private List<String> photo;

	public EvozDocument() {

	}

	public EvozDocument(String id, String email, List<String> photo) {

		this.id = id;
		this.email = email;
		this.photo = photo;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPhoto() {
		return photo;
	}

	public void setPhoto(List<String> photo) {
		this.photo = photo;
	}

	
	@Override
	public String toString() {
		return "EvozDocument [id=" + id + ", email=" + email + ", photo=" + photo + "]";
	}

}

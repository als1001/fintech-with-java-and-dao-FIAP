package br.com.fintech.model;

import java.time.LocalDateTime;

import br.com.fintech.utils.CryptoUtils;

public class User {
	private String name;
	private String email;
	private String hashedPassword;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int id = 1;
	
	public User(String name, String email, String password, int id) {
		this.id = id;
		this.name = name;
		this.email = email;
		setHashedPassword(password);
	}
	
	public User(int id) {
		this.id = id;
	}
	
	public User(String email, String password) {
		this.email = email;
		setHashedPassword(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.updatedAt = LocalDateTime.now();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.updatedAt = LocalDateTime.now();
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String password) {
		try {
			this.hashedPassword = CryptoUtils.encrypt(password);
			this.updatedAt = LocalDateTime.now();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}

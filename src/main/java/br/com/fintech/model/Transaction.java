package br.com.fintech.model;

import java.time.LocalDateTime;

public class Transaction {
	private int id;
	private String title;
	private Double value;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private User user;
	private TransactionCategory transactionCategory;
	private Category category;

	public Transaction(String title, Double value, String description, User user,
			TransactionCategory transactionCategory) throws Exception {
		super();
		this.title = title;
		if (value == 0) {
			throw new Exception("A transação deve ser um número diferente de 0");
		}
		this.value = value;
		this.description = description;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.user = user;
		this.transactionCategory = transactionCategory;
	}

	public Transaction(String title, Double value, String description, User user,
			Category category) throws Exception {
		super();
		this.title = title;
		if (value == 0) {
			throw new Exception("A transação deve ser um número diferente de 0");
		}
		this.value = value;
		this.description = description;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.user = user;
		this.category = category;
	}

	public Transaction(int id, String title, Double value, String description, LocalDateTime createdAt,
			LocalDateTime updatedAt, User user, TransactionCategory transactionCategory) throws Exception {
		super();
		this.id = id;
		this.title = title;
		if (value == 0) {
			throw new Exception("A transação deve ser um número diferente de 0");
		}
		this.value = value;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.transactionCategory = transactionCategory;
	}
	
	public Transaction(int id, String title, Double value, String description, LocalDateTime createdAt,
			LocalDateTime updatedAt, User user, Category category) throws Exception {
		super();
		this.id = id;
		this.title = title;
		if (value == 0) {
			throw new Exception("A transação deve ser um número diferente de 0");
		}
		this.value = value;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setTransactionCategory(TransactionCategory transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	public double getValue() {
		return value;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public TransactionCategory getTransactionCategory() {
		return transactionCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", title=" + title + ", value=" + value + ", description=" + description
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", user=" + user + ", transactionCategory="
				+ transactionCategory + "]";
	}

}

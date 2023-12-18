package br.com.fintech.model;

public class TransactionCategory {
	private String description;
	private String name;
	private int id = 26;
	
	public TransactionCategory(String description , String name) {
		super();
		this.name = name;
		this.description = description;
		System.out.printf("Categoria %s foi criada! %n", description);
	}
	
	public TransactionCategory(String description, String name, int id ) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;
		System.out.printf("Categoria %s foi criada! %n", description);
	}
	
	public TransactionCategory(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TransactionCategory [description=" + description + ", name=" + name + ", id=" + id + "]";
	}
	
	
}

package br.com.fintech.dao.transactionCategory;

import java.util.List;

import br.com.fintech.model.TransactionCategory;

public interface TransactionCategoryDAO {
	public void insert(TransactionCategory transactionCategory);
	
	public List<TransactionCategory> getAll();
}
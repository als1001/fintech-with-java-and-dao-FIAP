package br.com.fintech.factory;

import br.com.fintech.dao.category.CategoryDAO;
import br.com.fintech.dao.category.OracleCategoryDAO;
import br.com.fintech.dao.transaction.OracleTransactionDao;
import br.com.fintech.dao.transaction.TransactionDAO;
import br.com.fintech.dao.transactionCategory.OracleTransactionCategoryDao;
import br.com.fintech.dao.transactionCategory.TransactionCategoryDAO;
import br.com.fintech.dao.user.OracleUserDao;
import br.com.fintech.dao.user.UserDAO;

public abstract class DAOFactory {
	public static TransactionCategoryDAO getTransactionCategoryDAO() {
		return new OracleTransactionCategoryDao();
	}
	
	public static TransactionDAO getTransactionDAO() {
		return new OracleTransactionDao();
	}
	
	public static UserDAO getUserDAO() {
		return new OracleUserDao();
	}
	
	public static CategoryDAO getCategoryDAO() {
		return new OracleCategoryDAO();
	}
}
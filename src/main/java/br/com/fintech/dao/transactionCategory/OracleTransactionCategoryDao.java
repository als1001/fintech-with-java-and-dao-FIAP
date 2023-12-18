package br.com.fintech.dao.transactionCategory;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import br.com.fintech.jdbc.FintechDBManager;
import br.com.fintech.model.TransactionCategory;

public class OracleTransactionCategoryDao implements TransactionCategoryDAO {

	private Connection connection;

	public void insert(TransactionCategory transactionCategory) {
		PreparedStatement stmt = null;

		try {
			this.connection = FintechDBManager.getConnection();
			String sql = "INSERT INTO TBL_CATEGORY (ID, NAME, DESCRIPTION)\r\n"
					+ "VALUES (SQ_CATEGORY.nextval, ?, ?)";
			stmt = connection.prepareStatement(sql);
			System.out.println(transactionCategory.getName() + ' ' + transactionCategory.getDescription());
			stmt.setString(1, transactionCategory.getName());
			stmt.setString(2, transactionCategory.getDescription());

			if (stmt.executeUpdate() == 1) {
			    ResultSet generatedKeys = stmt.getGeneratedKeys();
			    if (generatedKeys.next()) {
			        long id = generatedKeys.getLong(1);
			        transactionCategory.setId((int) id);
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<TransactionCategory> getAll() {
	    PreparedStatement stmt = null;
	    ResultSet resultSet = null;
	    List<TransactionCategory> transactionCategories = new ArrayList<>();

	    try {
	        this.connection = FintechDBManager.getConnection();
	        String query = "SELECT ID, DESCRIPTION, NAME FROM TBL_CATEGORY";
	        stmt = connection.prepareStatement(query);
	        resultSet = stmt.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("ID");
	            String description = resultSet.getString("DESCRIPTION");
	            String name = resultSet.getString("NAME");
	            TransactionCategory category = new TransactionCategory(description, name, id);
	            transactionCategories.add(category);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return transactionCategories;
	}

}

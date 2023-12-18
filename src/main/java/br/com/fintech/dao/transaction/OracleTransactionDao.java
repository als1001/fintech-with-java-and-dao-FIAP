package br.com.fintech.dao.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.jdbc.FintechDBManager;
import br.com.fintech.model.Category;
import br.com.fintech.model.Transaction;
import br.com.fintech.model.User;

public class OracleTransactionDao implements TransactionDAO {

	private Connection connection;

	public void insert(Transaction transaction) {
		PreparedStatement stmt = null;

		try {
			this.connection = FintechDBManager.getConnection();
			String sql = "INSERT INTO TBL_TRANSACTION (ID, TITLE, DESCRIPTION, VALUE, CREATED_AT, UPDATED_AT, USER_ID, CATEGORY_ID)\r\n"
					+ "VALUES (SQ_TRANSACTION.nextval, ?, ?, ?, SYSDATE, SYSDATE, ?, ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, transaction.getTitle());
			stmt.setString(2, transaction.getDescription());
			stmt.setDouble(3, transaction.getValue());
			stmt.setInt(4, transaction.getUser().getId());
			stmt.setInt(5, transaction.getCategory().getId());

			if (stmt.executeUpdate() == 1) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					long id = generatedKeys.getLong(1);
					transaction.setId((int) id);
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

	public List<Transaction> getAll() {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Transaction> transactions = new ArrayList<>();

		try {
			this.connection = FintechDBManager.getConnection();
			String query = "SELECT ID, TITLE, DESCRIPTION, VALUE, CREATED_AT, UPDATED_AT, USER_ID, CATEGORY_ID FROM TBL_TRANSACTION";
			stmt = connection.prepareStatement(query);
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String title = resultSet.getString("TITLE");
				String description = resultSet.getString("DESCRIPTION");
				Double value = resultSet.getDouble("VALUE");

				Timestamp createdAtTimestamp = resultSet.getTimestamp("CREATED_AT");
				LocalDateTime createdAt = createdAtTimestamp.toLocalDateTime();

				Timestamp updatedAtTimestamp = resultSet.getTimestamp("UPDATED_AT");
				LocalDateTime updatedAt = updatedAtTimestamp.toLocalDateTime();

				int userId = resultSet.getInt("USER_ID");
				User user = new User(userId);

				int categoryId = resultSet.getInt("CATEGORY_ID");
				Category category = new Category(categoryId);

				Transaction transaction = new Transaction(id, title, value, description, createdAt, updatedAt, user,
						category);
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		return transactions;
	}


	public List<Transaction> getTransactionsByUser(int userId) throws Exception {
		List<Transaction> transactions = new ArrayList<>();

		try (Connection connection = FintechDBManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(
						"SELECT ID, TITLE, DESCRIPTION, VALUE, CREATED_AT, UPDATED_AT, USER_ID, CATEGORY_ID " +
								"FROM TBL_TRANSACTION " +
						"WHERE USER_ID = ?")) {

			stmt.setInt(1, userId);

			try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("ID");
					String title = resultSet.getString("TITLE");
					String description = resultSet.getString("DESCRIPTION");
					Double value = resultSet.getDouble("VALUE");

					Timestamp createdAtTimestamp = resultSet.getTimestamp("CREATED_AT");
					LocalDateTime createdAt = createdAtTimestamp.toLocalDateTime();

					Timestamp updatedAtTimestamp = resultSet.getTimestamp("UPDATED_AT");
					LocalDateTime updatedAt = updatedAtTimestamp.toLocalDateTime();

					int categoryId = resultSet.getInt("CATEGORY_ID");
					Category category = new Category(categoryId);

					Transaction transaction = new Transaction(id, title, value, description, createdAt, updatedAt, new User(userId), category);
					transactions.add(transaction);
				}
			}
		} catch (SQLException e) {
			handleSQLException(e);
		}
		return transactions;
	}

	public double getTotalBalanceByUser(int userId) {
		double totalBalance = 0.0;

		try (Connection connection = FintechDBManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(
						"SELECT SUM(VALUE) AS TOTAL_BALANCE " +
								"FROM TBL_TRANSACTION " +
						"WHERE USER_ID = ?")) {

			stmt.setInt(1, userId);

			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					totalBalance = resultSet.getDouble("TOTAL_BALANCE");
				}
			}
		} catch (SQLException e) {
			handleSQLException(e);
		}
		return totalBalance;
	}

	private void handleSQLException(SQLException e) {
		e.printStackTrace();
	}

}

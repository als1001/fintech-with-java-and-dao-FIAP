package br.com.fintech.dao.user;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import br.com.fintech.exception.DBException;
import br.com.fintech.jdbc.FintechDBManager;
import br.com.fintech.model.User;

public class OracleUserDao implements UserDAO {

	private Connection connection;

	@Override
	public boolean validate(User user) {
		this.connection = FintechDBManager.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.connection.prepareStatement("SELECT * FROM TBL_USER WHERE EMAIL = ? AND ENCRYPTED_PASSWORD = ?");
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getHashedPassword());
			rs = stmt.executeQuery();

			if (rs.next()){
				int id = rs.getInt("ID");
				user.setId(id);
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
    public void insert(User user) throws DBException {
        PreparedStatement stmt = null;

        try {
            this.connection = FintechDBManager.getConnection();

            String sql = "INSERT INTO TBL_USER (ID, NAME, EMAIL, ENCRYPTED_PASSWORD, CREATED_AT, UPDATED_AT) " +
                         "VALUES (SQ_USER.nextval, ?, ?, ?, SYSDATE, SYSDATE)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getHashedPassword());

            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt("ID");
                user.setId(userId);
            }
        } catch (SQLException e) {
            throw new DBException("Erro ao inserir usuário no banco de dados.", e);
        } finally {
            try {
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
    }
}


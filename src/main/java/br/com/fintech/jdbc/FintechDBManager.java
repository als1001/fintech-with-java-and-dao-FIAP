package br.com.fintech.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class FintechDBManager {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM99498",
					"210205");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
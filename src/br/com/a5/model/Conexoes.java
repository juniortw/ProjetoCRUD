package br.com.a5.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexoes {

	private static String userSql = "TesteDB";
	private static String passwordSql = "TesteDB";
	private static String databaseSql = "TesteBD";
	
	private static String userXampp = "root";
	private static String passwordXampp = "";
	
	public static Connection c;

	public static Connection connectSql() {

		String jdbc = "jdbc:sqlserver://10.25.7.148:1433;databaseName=" + databaseSql;
	
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			c = DriverManager.getConnection(jdbc, userSql, passwordSql);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro no sql ao iniciar a conexão "+ e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro no ao iniciar a conexão "+ e.getMessage());
		}

		return c;
	}
	
	public static Connection connectXampp() {

		String jdbc = "jdbc:mysql://localhost:3306/mysql";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			c = DriverManager.getConnection(jdbc, userXampp, passwordXampp);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro no sql ao iniciar a conexão "+ e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro no ao iniciar a conexão "+ e.getMessage());
		}

		return c;
	}

}
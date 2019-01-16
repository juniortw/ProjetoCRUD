package br.com.a5.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.a5.bean.Usuario;
import br.com.a5.utils.ExceptionUtil;

public class UsuarioDAO {

	public static Usuario bean = new Usuario();

	public static final String tabela = "dbo.Cliente";

	public final static Connection con = Conexoes.connectSql();

	public static String consultaPorCpf(String cpf) {

		String SQL = "SELECT * from " + tabela + " WHERE CPF = '" + cpf +"'";

		fazConsulta(SQL, bean);

		return bean.toString().contains("null") ? "Usuario não encontrado" : bean.toString();
	}

	public static String consultaPorNascimento(String ano) {

		String SQL = "SELECT * from " + tabela + " WHERE Ano = '" + ano +"'";

		fazConsulta(SQL, bean);

		return bean.toString().contains("null") ? "Usuario não encontrado" : bean.toString();
	}

	public static String consultaPorCelular(String ani) {

		String SQL = "SELECT * from " + tabela + " WHERE ani = '" + ani +"'";

		fazConsulta(SQL, bean);

		return bean.toString().contains("null") ? "Usuario não encontrado" : bean.toString();
	}

	private static void fazConsulta(String sql, Usuario bean) {

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				bean.setAni(rs.getString(1));
				bean.setCpf(rs.getString(2));
				bean.setAno(rs.getInt(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro: " + ExceptionUtil.stackTraceToString(e));

		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Ocorreu um erro : " + ExceptionUtil.stackTraceToString(e));
				}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> buscaTodos() {
		String sql = "SELECT * FROM " + tabela;

		List<T> lista = new ArrayList<T>();

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setAni(rs.getString(1));
				user.setCpf(rs.getString(2));
				user.setAno(rs.getInt(3));
				lista.add(((T) user));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro: " + ExceptionUtil.stackTraceToString(e));

		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Ocorreu um erro : " + ExceptionUtil.stackTraceToString(e));
				}
		}

		return lista;
	}

	public static String adiciona(String ani, int ano, String cpf) {
		String sql = "INSERT INTO " + tabela + " (ani, cpf, ano) VALUES (?, ?, ?)";

		try {
			con.setAutoCommit(false);

			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1,ani);
			stmt.setString(2,cpf);
			stmt.setInt(3,ano);

			stmt.executeUpdate();

			con.commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro : " + ExceptionUtil.stackTraceToString(e));
			try {
				con.rollback();
				System.out.println("Rollback efetuado");
			} catch (SQLException e1) {
				System.out.println("Ocorreu um erro : " + ExceptionUtil.stackTraceToString(e1));
			}
		}
		return "Sucesso";
	}

	public static String atualizar(String ani, int ano, String cpf) {
		int linhasAfetadas = 0;

		try {
			
			con.setAutoCommit(false);
			
			String sql = "UPDATE " + tabela + " SET `ani`=(?),`ano`=(?),`cpf`=(?) WHERE cpf = '" + cpf +"'";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, ani);
			stmt.setInt(2, ano);
			stmt.setString(3, cpf);

			stmt.executeUpdate();

			linhasAfetadas = stmt.getUpdateCount();

			con.commit();
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro : " + ExceptionUtil.stackTraceToString(e));
			try {
				con.rollback();
				System.out.println("Rollback efetuado");
			} catch (SQLException e1) {
				System.out.println("Ocorreu um erro : " + ExceptionUtil.stackTraceToString(e1));
			}
		}

		return "Numeros de linhas afetadas : " + linhasAfetadas;

	}

	public static String deletar(String cpf) {

		int linhasAfetadas = 0;

		try {
			
			con.setAutoCommit(false);
			
			String sql = "DELETE FROM " + tabela + " WHERE cpf = '" + cpf +"'";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.executeUpdate();

			linhasAfetadas = stmt.getUpdateCount();

			con.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro : " + ExceptionUtil.stackTraceToString(e));
			try {
				con.rollback();
				System.out.println("Rollback efetuado");
			} catch (SQLException e1) {
				System.out.println("Ocorreu um erro : " + ExceptionUtil.stackTraceToString(e1));
			}
		}

		return "Numeros de linhas afetadas : " + linhasAfetadas;
	}

}

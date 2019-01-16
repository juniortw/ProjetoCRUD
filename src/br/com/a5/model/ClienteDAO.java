package br.com.a5.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.a5.bean.Cliente;
import br.com.a5.utils.ExceptionUtil;

public class ClienteDAO {
	
public static Cliente bean = new Cliente();
	
	public static final String tabela = "cliente";

	public final static Connection con = Conexoes.connectXampp();
	
	public static String consultaPorCpf(String cpf) {

		String SQL = "SELECT * from "+tabela+" WHERE CPF = '" + cpf + "'";

		fazConsulta(SQL, bean);

		return bean.toString().contains("null") ? "Usuario não encontrado" : bean.toString();
	}
	
	public static String consultaPorCelular(String ani) {

		String SQL = "SELECT * from "+tabela+" WHERE telefone = '" + ani + "'";

		fazConsulta(SQL, bean);

		return bean.toString().contains("null") ? "Usuario não encontrado" : bean.toString();
	}

	private static void fazConsulta(String sql, Cliente bean) {

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setNome(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTelefone(rs.getString(4));
			}

		} catch (Exception e) {			
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
				Cliente user = new Cliente();
				user.setId(rs.getInt(1));
				user.setNome(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setTelefone(rs.getString(4));
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

	public static String adiciona(String nome, String telefone, String email) {

		String sql = "INSERT INTO " + tabela + " (id, nome, email, telefone) VALUES (?, ?, ?, ?)";

		int numLinhas = 0;

		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			con.setAutoCommit(false);

			stmt.setInt(1, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(2, nome);
			stmt.setString(3, email);
			stmt.setString(4, telefone);

			stmt.executeUpdate();

			try (ResultSet resultset = stmt.getGeneratedKeys()) {
				if (resultset.next()) {
					int id = resultset.getInt("id");
					bean.setId(id);
				}
			}

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

		return "Numero de linha afetadas : " + numLinhas;
	}
	
	public static String atualizar(String id, String nome, String email, String telefone) {		
		
		int linhasAfetadas = 0;
		
		try {
			
			con.setAutoCommit(false);
			
			String sql = "UPDATE "+tabela+" SET `id`=(?),`nome`=(?),`email`=(?),`telefone`=(?) WHERE id = "+id;
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, id);
			stmt.setString(2, nome);
			stmt.setString(3, email);
			stmt.setString(4, telefone);
			
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

	public static String deletar(int id) {

		int linhasAfetadas = 0;

		try {
			
			con.setAutoCommit(false);
			
			String sql = "DELETE FROM " + tabela + " WHERE id = " + id;

			PreparedStatement stmt = con.prepareStatement(sql);

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

}

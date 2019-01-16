package br.com.a5.bean;

public class Cliente {
	
	private int id;
	private String nome;
	private String telefone;
	private String email;
	
	public int getId() {
		return id;
	}
	
	public int setId(int id) {
		return this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String setNome(String nome) {
		return this.nome = nome;
	}

	public String getTelfone() {
		return telefone;
	}

	public String setTelefone(String telefone) {					    
	    return this.telefone = telefone;	    
	}
	
	public String getEmail() {
		return email;
	}
	
	public String setEmail(String email) {
		return this.email = email;
	}

	@Override
	public String toString() {
		return "Bean [Id=" + id + ", Nome=" + nome + ", Telefone=" + telefone + ", Email=" + email + "]";
	}

}

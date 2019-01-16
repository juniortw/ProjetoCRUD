package br.com.a5.bean;

import java.time.LocalDateTime;

public class Usuario {

	private String Ani;
	private String Cpf;
	private String Ano;

	public String getAni() {
		return Ani;
	}

	public String setAni(String ani) {
		return Ani = ani;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getAno() {
		return Ano;
	}

	public String setAno(int ano) {					    
	    return this.Ano = String.valueOf(LocalDateTime.now().getYear() - ano);	    
	}

	@Override
	public String toString() {
		return "Bean [Ani=" + Ani + ", Cpf=" + Cpf + ", Idade=" + Ano + "]";
	}
	
}

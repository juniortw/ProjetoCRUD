package br.com.a5.view;

import java.util.List;

import br.com.a5.controller.ControllerCliente;
import br.com.a5.controller.ControllerUsuario;

public class View {
	
	public static String consultaCpfUsuario(String cpf) {		
		return ControllerUsuario.validaCPF(cpf);		
	}
		
	public static String consultaTelefoneUsuario(String tel) {		
		return ControllerUsuario.validaAni(tel);	
	}
	
	public static String consultaPorIdadeUsuario(String ano) {		
		return ControllerUsuario.validaIdade(ano);	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> consultaTodosUsuario() {
		return ((List<T>) ControllerUsuario.consultaBanco());
	}
	
	public static String adicionaNovoUsuario(String ani, int ano, String cpf) {
		return ControllerUsuario.adicionaNovo(ani, ano, cpf);
	}
	
	public static String atualizaUsuario(String ani, int ano, String cpf) {
		return ControllerUsuario.atualiza(ani, ano, cpf);
	}
	
	public static String removeUsuario(String cpf) {
		return ControllerUsuario.remove(cpf);
	}
	
	// --------------------------------------------
	
	public static String consultaCpfCliente(String cpf) {		
		return ControllerCliente.validaCPF(cpf);		
	}
		
	public static String consultaTelefoneCliente(String tel) {		
		return ControllerCliente.validaAni(tel);	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> consultaTodosCliente() {		
		return ((List<T>) ControllerCliente.consultaBanco());
	}
	
	public static String adicionaNovoCliente(String nome, String telefone, String email) {
		return ControllerCliente.adicionaNovo(nome, telefone, email);
	}
	
	public static String atualizaCliente(String id, String nome, String telefone, String email) {
		return ControllerCliente.atualiza(id, nome, telefone, email);
	}
	
	public static String removeCliente(int id) {
		return ControllerCliente.remove(id);
	}
	
	

}

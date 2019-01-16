package br.com.a5.controller;

import java.util.List;

import br.com.a5.model.ClienteDAO;
import br.com.a5.utils.ValidaDocumento;
import br.com.a5.utils.ValidaTelefone;

public class ControllerCliente {
	
	public static String validaCPF(String cpf) {
		if (null != cpf && !"".equals(cpf)) {
			if(ValidaDocumento.isCPF(cpf)) {				
				return ClienteDAO.consultaPorCpf(cpf);
			}		
		}
		return "Usuario invalido";
	}

	public static String validaAni(String ani) {
		if (null != ani && !"".equals(ani)) {
			if(ValidaTelefone.isTelefone(ani)) {				
				return ClienteDAO.consultaPorCelular(ani);
			}			
		}		
		return "Usuario invalido";
	}
	
	public static <T> List<T> consultaBanco() {
		return ClienteDAO.buscaTodos();
	}
	
	public static String adicionaNovo(String nome, String telefone, String email) {
		if(!"".equals(nome) && !"".equals(telefone) && !"".equals(email) && null != nome && null != telefone && null != email) {
			return ClienteDAO.adiciona(nome, telefone, email);			
		}
		return "Usuario invalido";
	}
	
	public static String atualiza(String id, String nome, String telefone, String email) {
		if(!"".equals(id) &&!"".equals(nome) && !"".equals(telefone) && !"".equals(email) && null != id && null != nome && null != telefone && null != email) {
			return ClienteDAO.atualizar(id, nome, email, telefone);
		}
		return "Usuario invalido";
	}

	public static String remove(int id) {		
		return ClienteDAO.deletar(id);
	}

}

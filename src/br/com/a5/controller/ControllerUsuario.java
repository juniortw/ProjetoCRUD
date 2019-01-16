package br.com.a5.controller;

import java.time.LocalDateTime;
import java.util.List;

import br.com.a5.model.UsuarioDAO;
import br.com.a5.utils.ValidaDocumento;
import br.com.a5.utils.ValidaTelefone;

public class ControllerUsuario {

	public static String validaCPF(String cpf) {
		if (null != cpf && !"".equals(cpf)) {
			if(ValidaDocumento.isCPF(cpf)) {				
				return UsuarioDAO.consultaPorCpf(cpf);
			}		
		}
		return "Usuario invalido";
	}

	public static String validaAni(String ani) {
		if (null != ani && !"".equals(ani)) {
			if(ValidaTelefone.isTelefone(ani)) {				
				return UsuarioDAO.consultaPorCelular(ani);
			}			
		}		
		return "Usuario invalido";
	}
	
	public static String validaIdade(String ano) {
		if (null != ano && !"".equals(ano)) {
			if(LocalDateTime.now().getYear() > Integer.parseInt(ano)) {
				return UsuarioDAO.consultaPorNascimento(ano);	
			}							
		}		
		return "Data invalida";
	}
	
	public static <T> List<T> consultaBanco() {
		return UsuarioDAO.buscaTodos();
	}
	
	public static String adicionaNovo(String ani, int ano, String cpf) {
		if(!"".equals(ani) && !"".equals(cpf) && null != ani && null != cpf) {
			return UsuarioDAO.adiciona(ani, ano, cpf);			
		}
		return "Usuario invalido";
	}
	
	public static String atualiza(String ani, int ano, String cpf) {
		if(!"".equals(ani) && !"".equals(cpf) && null != ani && null != cpf) {
			return UsuarioDAO.atualizar(ani, ano, cpf);
		}		
		return "Usuario invalido";
	}

	public static String remove(String cpf) {		
		return UsuarioDAO.deletar(cpf);
	}

}

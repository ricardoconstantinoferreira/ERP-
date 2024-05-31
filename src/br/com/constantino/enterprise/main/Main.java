package br.com.constantino.enterprise.main;

import br.com.constantino.enterprise.dao.GrupoDAO;
import br.com.constantino.enterprise.entities.Grupo;



public class Main {
	
	public static void main(String args[]) {
		
		GrupoDAO dao = new GrupoDAO();
		
		Grupo grupo = dao.pegaGrupoPorId(2);
		
		System.out.println(String.valueOf(21));
		
	}

}

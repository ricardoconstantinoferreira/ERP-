package br.com.constantino.enterprise.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.persistence.RollbackException;

import br.com.constantino.enterprise.dao.GrupoDAO;
import br.com.constantino.enterprise.entities.Grupo;
import br.com.constantino.enterprise.utils.Messages;

@ManagedBean
public class GrupoController {
	
	private Grupo grupo = new Grupo();	
	private List<Grupo> grupos;
	private String botao = "Salvar";
		
	public List<Grupo> getGrupos() {
		GrupoDAO dao = new GrupoDAO();
		grupos = dao.listar();
		return grupos;
	}

	public void salva(Grupo grupo) {
		GrupoDAO dao = new GrupoDAO();				
		
		dao.merge(grupo);
		
		if (grupo.getId() == null || grupo.getId() == 0) {			
			Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Grupo Cadastrado com sucesso!", "Parabéns");				
			this.limpar();				
		} else {
			Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Grupo Alterado com sucesso!", "Parabéns");
			this.setBotao("Atualizar");
		}			
	}
	
	public String limpar() {		
		this.grupo.setNome("");
		
		if (this.grupo.getId() == null || this.grupo.getId() == 0) 
			this.setBotao("Salvar");
		else
			this.setBotao("Atualizar");
		
		return "index.xhtml";
	}
	
	public String editar(Grupo grupo) {				
		this.grupo = grupo;	
		this.setBotao("Atualizar");
		return "index.xhtml";
	}
	
	public String excluir(Integer id) {
		try {
			GrupoDAO dao = new GrupoDAO();
			dao.remover(id);
		} catch (RollbackException e) {
			Messages.getMessagem(FacesMessage.SEVERITY_ERROR, "Este grupo possui uma ou mais redes, exclua todas primeiro!", "Não é possível excluir item!");
		}
		
		return "listar.xhtml";			
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getBotao() {
		return botao;
	}

	public void setBotao(String botao) {
		this.botao = botao;
	}
	
	
	
	
}

package br.com.constantino.enterprise.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import br.com.constantino.enterprise.dao.GrupoDAO;
import br.com.constantino.enterprise.dao.RedeDAO;
import br.com.constantino.enterprise.entities.Grupo;
import br.com.constantino.enterprise.entities.Rede;
import br.com.constantino.enterprise.utils.Messages;

@ManagedBean
public class RedeController {
	
	private Rede rede = new Rede();
	private Integer grupoId;
	private String botao = "Salvar";
	
	public void salva(Rede rede) {		
		RedeDAO dao = new RedeDAO();
		GrupoDAO grupoDao = new GrupoDAO();
		
		Grupo grupo = grupoDao.pegaGrupoPorId(this.grupoId);
		this.rede.setGrupo(grupo);
				
		dao.merge(rede);
		
		if (rede.getId() == null || rede.getId() == 0) {
			Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Rede cadastrada com sucesso!!!", "Rede cadastrada.");			
			this.limpar();
		} else {
			Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Rede alterada com sucesso!!!", "Rede cadastrada.");
			this.setBotao("Atualizar");
		}			
	}
	
	public String excluir(Integer id) {
		RedeDAO dao = new RedeDAO();
		dao.deletar(id);
		return "listar.xhtml";
	}
	
	public String limpar() {
		
		this.rede.setNome("");
		this.setGrupoId(0);
		
		if (this.rede.getId() == null || this.rede.getId() == 0)
			this.setBotao("Salvar");
		else
			this.setBotao("Atualizar");
		
		return "index.xhtml";
	}
	
	public String editar(Rede rede) {
		this.rede = rede;
		this.grupoId = rede.getGrupo().getId();
		this.setBotao("Atualizar");
		return "index.xhtml";
	}
	
	public List<Grupo> getGrupos() {
		GrupoDAO grupoDAO = new GrupoDAO();
		return grupoDAO.listar();
	}
	
	public List<Rede> getRedes() {
		RedeDAO dao = new RedeDAO();
		return dao.getRedes();
	}

	public Rede getRede() {
		return rede;
	}

	public void setRede(Rede rede) {
		this.rede = rede;
	}

	public String getBotao() {
		return botao;
	}

	public void setBotao(String botao) {
		this.botao = botao;
	}
	
	public Integer getGrupoId() {
		return grupoId;
	}
	
	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}
	

}

package br.com.constantino.enterprise.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import br.com.constantino.enterprise.dao.EstabelecimentoDAO;
import br.com.constantino.enterprise.dao.GrupoDAO;
import br.com.constantino.enterprise.dao.RedeDAO;
import br.com.constantino.enterprise.entities.Estabelecimento;
import br.com.constantino.enterprise.entities.Grupo;
import br.com.constantino.enterprise.entities.Rede;
import br.com.constantino.enterprise.utils.Messages;

@ManagedBean
public class EstabelecimentoController {

	private Estabelecimento estabelecimento = new Estabelecimento();
	private List<Grupo> grupos;
	private List<Rede> redes;
	private String botao = "Salvar";
	private Integer grupoId;
	private Integer redeId;

	public EstabelecimentoController() {
		GrupoDAO grupoDAO = new GrupoDAO();
		RedeDAO redeDAO = new RedeDAO();
		
		this.grupos = grupoDAO.listar();
		this.redes = redeDAO.getRedes();		
	}
	
	public List<Estabelecimento> getEstabelecimentos() {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		return dao.listar();
	}
	
	public void carregarCombo(Integer grupoId) {
		RedeDAO redeDAO = new RedeDAO();
		this.redes = redeDAO.getRedeGrupo(grupoId);
	}
	
	public String limpar() {
		this.estabelecimento.setCNPJ("");
		this.setGrupoId(null);
		this.estabelecimento.setNomeFantasia("");
		this.estabelecimento.setRazaoSocial("");
		this.setRedeId(null);
		
		if (this.estabelecimento.getId() == null || this.estabelecimento.getId() == 0) 
			this.setBotao("Salvar");
		else 
			this.setBotao("Atualizar");
		
		return "index.xhtml";
	}
	
	public String editar(Estabelecimento estabelecimento) {		
		this.estabelecimento = estabelecimento;
		this.redeId = this.estabelecimento.getRede().getId();
		this.grupoId = this.estabelecimento.getGrupo().getId();		
		this.setBotao("Atualizar");
		
		return "index.xhtml";
	}
	
	public String excluir(Integer id) {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		dao.deletar(id);		
		return "listar.xhtml";
	}
	
	public void salva(Estabelecimento estabelecimento) {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();						
		GrupoDAO grupoDAO = new GrupoDAO();
		RedeDAO redeDAO = new RedeDAO();
		
		Grupo grupo = grupoDAO.pegaGrupoPorId(this.grupoId);
		this.estabelecimento.setGrupo(grupo);
		
		Rede rede = redeDAO.buscarPeloCodigo(this.redeId);
		this.estabelecimento.setRede(rede);
			
		dao.merge(estabelecimento);
		
		if (estabelecimento.getId() == null || estabelecimento.getId() == 0) {
			Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Estabecimento cadastrado com sucesso.", "Estabecimento cadastrado com sucesso.");
			this.limpar();		
		} else {
			Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Estabecimento alterado com sucesso.", "Estabecimento alterado com sucesso.");
			this.setBotao("Atualizar");
		}			
	
	}
			
	public List<Grupo> getGrupos() {					
		return this.grupos;
	}
	
	public List<Rede> getRedes() {				
		return this.redes;		
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
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

	public Integer getRedeId() {
		return redeId;
	}

	public void setRedeId(Integer redeId) {
		this.redeId = redeId;
	}
	
	
		
}
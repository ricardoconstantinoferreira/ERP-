package br.com.constantino.enterprise.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import br.com.constantino.enterprise.dao.ProdutoDAO;
import br.com.constantino.enterprise.entities.Produto;
import br.com.constantino.enterprise.utils.Messages;

@ManagedBean
public class ProdutoController {
	
	private Produto produto = new Produto();
	private String botao = "Salvar";
//	private List<Produto> produtos;
	
	public void salva(Produto produto) {
		
		ProdutoDAO dao = new ProdutoDAO();
		
		if (produto.getNome().trim().equals("")) {
			Messages.getMessagem(FacesMessage.SEVERITY_ERROR, "O nome do produto é obrigatório!", "Produto");
		} else if (produto.getPreco() == null) {
			Messages.getMessagem(FacesMessage.SEVERITY_ERROR, "O preço do produto é obrigatório", "Preço");
		} else {
			
			dao.merge(produto);
			
			if (produto.getId() == null || produto.getId() == 0) {
				Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Produto cadastrado com sucesso!", "Preço");
				this.limpar();
			} else {
				Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Produto alterado com sucesso!", "Preço");
				this.setBotao("Atualizar");
			}							
		}		
	}
	
	public String limpar() {
		this.produto.setNome("");
		this.produto.setPreco(null);
		
		if (this.produto.getId() == null || this.produto.getId() == 0)
			this.setBotao("Salvar");
		else
			this.setBotao("Atualizar");
		
		return "index.xhtml";
	}
	
	public List<Produto> getProdutos() {
		ProdutoDAO dao = new ProdutoDAO();
		return dao.getProdutos();
	}
	
	public String excluir(Integer id) {
		ProdutoDAO dao = new ProdutoDAO();
		dao.deletar(id);
		return "listar.xhtml";
	}
	
	public String editar(Produto produto) {
		this.produto = produto;
		this.setBotao("Atualizar");
		return "index.xhtml";
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getBotao() {
		return botao;
	}
	public void setBotao(String botao) {
		this.botao = botao;
	}
	
	

}

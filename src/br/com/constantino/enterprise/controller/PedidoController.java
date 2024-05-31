package br.com.constantino.enterprise.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import br.com.constantino.enterprise.dao.EstabelecimentoDAO;
import br.com.constantino.enterprise.dao.PedidoDAO;
import br.com.constantino.enterprise.dao.ProdutoDAO;
import br.com.constantino.enterprise.entities.Estabelecimento;
import br.com.constantino.enterprise.entities.Pedido;
import br.com.constantino.enterprise.entities.Produto;
import br.com.constantino.enterprise.utils.Messages;

@ManagedBean
@ViewScoped
public class PedidoController {
	
	private Pedido pedido = new Pedido();
	private Estabelecimento estabelecimento = new Estabelecimento();
	private Produto produto = new Produto();
	private Integer estabelecimentoId;	
	private List<String> produtosEscolhidos = new ArrayList<>();	
	private PieChartModel grafico;
	private PieChartModel graficoPrecoTotal;
	
	public PedidoController() {
		PedidoDAO dao = new PedidoDAO();
		
		List<Object[]> listagem = dao.retornarEstabelecimentosPedidos();
		this.grafico = new PieChartModel();
		
		for (Object[] lista: listagem) {
			String razaoSocial = (String) lista[1];
			Integer quantidade = (Integer) lista[0];
			this.grafico.set(razaoSocial, quantidade);
		}
		
		this.grafico.setTitle("Quantidade");
		this.grafico.setLegendPosition("w");
		
	}
	
	public List<Object[]> getListaTotalPreco() {		
		PedidoDAO dao = new PedidoDAO();
		return dao.retornarValoresPedidosEstabelecimentos();
	}

	public List<Estabelecimento> getEstabelecimentos() {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		return dao.listar();		
	}		

	public List<Produto> getProdutos() {
		ProdutoDAO dao = new ProdutoDAO();
		return dao.getProdutos();
	}
	
	public void salva(Pedido pedido) {
		
		ArrayList<Integer> listaId = new ArrayList<Integer>();
		
		for (String produtoId : produtosEscolhidos) {			
			Integer id = Integer.parseInt(produtoId);
			listaId.add(id);
		}			
		
		if (listaId.size() > 0) {
		
			PedidoDAO pedidoDAO = new PedidoDAO();		
			ProdutoDAO produtoDAO = new ProdutoDAO();				
			EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();			
			
			Estabelecimento estabelecimento = estabelecimentoDAO.buscarPorId(this.estabelecimentoId);
			this.pedido.setEstabelecimento(estabelecimento);			
		
			List<Produto> produto = produtoDAO.buscarListaProdutoPorId(listaId);
			this.pedido.setProduto(produto);
			
			Double precoTotal = produtoDAO.totalPrecoProduto(listaId);
			this.pedido.setPrecoTotal(precoTotal);
			
			Calendar calendar = Calendar.getInstance();			
			this.pedido.setDataPedido(calendar);
			
			pedidoDAO.merge(this.pedido);
			
			Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Pedido realizado com sucesso.", "Pedido realizado com sucesso.");
			this.limpar();
			
		} else {
			Messages.getMessagem(FacesMessage.SEVERITY_INFO, "Escolha ao menos 1 produto!", "Escolha ao menos 1 produto!");
		}
						
	}		

	public String limpar() {
		
		this.produtosEscolhidos = null;
		this.estabelecimentoId = null;
		return "index.xhtml";
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}
	
	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void setEstabelecimentoId(Integer estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}
	
	public Integer getEstabelecimentoId() {
		return estabelecimentoId;
	}
	
	public List<String> getProdutosEscolhidos() {
		return produtosEscolhidos;
	}

	public void setProdutosEscolhidos(List<String> produtosEscolhidos) {
		this.produtosEscolhidos.addAll(produtosEscolhidos);
	}
	
	public void setGrafico(PieChartModel grafico) {
		this.grafico = grafico;
	}
	
	public PieChartModel getGrafico() {
		return grafico;
	}
	
	public void setGraficoPrecoTotal(PieChartModel graficoPrecoTotal) {
		this.graficoPrecoTotal = graficoPrecoTotal;
	}
	
	public PieChartModel getGraficoPrecoTotal() {
		return graficoPrecoTotal;
	}
	
}
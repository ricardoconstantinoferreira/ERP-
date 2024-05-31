package br.com.constantino.enterprise.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.constantino.enterprise.dao.ProdutoDAO;
import br.com.constantino.enterprise.entities.Pedido;

public class Test {
	
	public ArrayList<Integer> getIds() {
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		String[] produtosEscolhidos = new String[3];
				
		produtosEscolhidos[0] = "1";
		produtosEscolhidos[1] = "11";
		produtosEscolhidos[2] = "13";
							
		
		for (String produtoId : produtosEscolhidos) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Integer id = Integer.parseInt(produtoId);
			ids.add(id);
		}
		
		return ids;
	}
	
	public Calendar getDataAtual() {
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
//		String dt = date.format(calendar.getTime());
		return calendar;		
	}
	
	public Double getStringProduto(ArrayList<Integer> lista) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("enterprisedatabase");
		EntityManager em = emf.createEntityManager();
		
		String sql = "select sum(preco) as total from produto where id IN (:ids)";
		Query query = em.createNativeQuery(sql);
		query.setParameter("ids", lista);
		
		Double preco = (Double) query.getSingleResult();
		Double total = (double) Math.floor(preco);
		
		return total;
		
	}
	
	public List<Object[]> retornarEstabelecimentosPedidos() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("enterprisedatabase");
		EntityManager em = emf.createEntityManager();
		
		String sql = "select ped.id_estabelecimento, e.razao_social, count(pp.produto_id) as quantidade from pedido ped ";
			   sql += "inner join estabelecimento e on e.id = ped.id_estabelecimento ";
			   sql += "inner join pedido_produto pp on pp.pedido_id = ped.id ";
			   sql += "group by ped.id_estabelecimento";
				
		Query query = em.createNativeQuery(sql);	    			  
		return query.getResultList();
	}

	public static void main(String[] args) {
		Test teste = new Test();
		
		List<Object[]> listagem = teste.retornarEstabelecimentosPedidos();		
		
		for (Object[] lista : listagem) {			
			System.out.println("EC " + lista[0]);
			System.out.println("Razao Social " + lista[1]);
			System.out.println("Quantidade " + lista[2]);
			
		}
		
	}
}

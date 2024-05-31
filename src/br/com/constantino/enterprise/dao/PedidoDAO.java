package br.com.constantino.enterprise.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.constantino.enterprise.entities.Pedido;

public class PedidoDAO extends AbstractDAO {

	public void merge(Pedido pedido) {
		em.getTransaction().begin();
		em.merge(pedido);
		em.getTransaction().commit();
	}
	
	public List<Object[]> retornarValoresPedidosEstabelecimentos() {
		
		String sql = "select ped.id_estabelecimento, e.razao_social, sum(ped.preco_total) as preco from pedido ped ";
		   sql += "inner join estabelecimento e on e.id = ped.id_estabelecimento ";
		   sql += "inner join pedido_produto pp on pp.pedido_id = ped.id ";
		   sql += "group by ped.id_estabelecimento";
		
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	}
	
	public List<Object[]> retornarEstabelecimentosPedidos() {
		
		String sql = "select ped.id_estabelecimento, e.razao_social, count(pp.produto_id) as quantidade from pedido ped ";
			   sql += "inner join estabelecimento e on e.id = ped.id_estabelecimento ";
			   sql += "inner join pedido_produto pp on pp.pedido_id = ped.id ";
			   sql += "group by ped.id_estabelecimento";
		
	    Query query = em.createNativeQuery(sql);			   
		return query.getResultList();
	}
}

package br.com.constantino.enterprise.dao;

import java.util.List;

import br.com.constantino.enterprise.entities.Estabelecimento;

public class EstabelecimentoDAO extends AbstractDAO {
	
	public void merge(Estabelecimento estabelecimento) {
		em.getTransaction().begin();
		em.merge(estabelecimento);
		em.getTransaction().commit();
	}
	
	public void deletar(Integer id) {
		Estabelecimento estabelecimento = this.buscarPorId(id);
		
		em.getTransaction().begin();
		em.remove(estabelecimento);
		em.getTransaction().commit();
	}
	
	public Estabelecimento buscarPorId(Integer id) {
		return em.find(Estabelecimento.class, id);
	}
	
	public List<Estabelecimento> listar() {
		return this.em.createQuery("select e from Estabelecimento e", Estabelecimento.class).getResultList();
	}
	
}

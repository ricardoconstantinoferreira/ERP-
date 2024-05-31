package br.com.constantino.enterprise.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.constantino.enterprise.entities.Rede;

public class RedeDAO extends AbstractDAO {
	
	public void merge(Rede rede) {
		em.getTransaction().begin();
		em.merge(rede);
		em.getTransaction().commit();
	}
	
	public void deletar(Integer id) {
		Rede rede = this.buscarPeloCodigo(id);
		
		em.getTransaction().begin();
		em.remove(rede);
		em.getTransaction().commit();
	}
	
	public List<Rede> getRedeGrupo(Integer grupoId) {
		
		String jpql = "select r from Rede r where r.grupo.id = :id";
		Query query = this.em.createQuery(jpql, Rede.class);
		query.setParameter("id", grupoId);
		return query.getResultList();
	}
	
	public List<Rede> getRedes() {
		String jpql = "select r from Rede r";
		Query query = em.createQuery(jpql, Rede.class);
		return query.getResultList();
	}
	
	public Rede buscarPeloCodigo(Integer id) {
		return em.find(Rede.class, id);
	}
	
	public List<Rede> buscarRedeListaId(Integer id) {
		String jpql = "select r from Rede r where r.id = :id";
		Query query = em.createQuery(jpql, Rede.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
}

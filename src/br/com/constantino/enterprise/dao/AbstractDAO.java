package br.com.constantino.enterprise.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDAO {

	protected EntityManager em;
	
	public AbstractDAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("enterprisedatabase");
		em = emf.createEntityManager();
	}
}

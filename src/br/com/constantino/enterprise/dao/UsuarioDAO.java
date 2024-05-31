package br.com.constantino.enterprise.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.constantino.enterprise.entities.Usuario;

public class UsuarioDAO extends AbstractDAO {

	public boolean existe(Usuario usuario) {
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u "
				+ "where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try {
			Usuario resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		
		em.close();
		return true;
	}
}

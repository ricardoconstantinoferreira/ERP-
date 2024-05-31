package br.com.constantino.enterprise.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.constantino.enterprise.dao.UsuarioDAO;
import br.com.constantino.enterprise.entities.Usuario;

@ManagedBean
public class LoginController {
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public String efetuaLogin() {
		System.out.println("Efetuando login " + this.usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();
		boolean existe = new UsuarioDAO().existe(usuario);
		
		System.out.println(existe);
		
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			return "/home/home?faces-redirect=true";
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado!"));
		
		return "/login/login?faces-redirect=true";
	}
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return "/login/login?faces-redirect=true";
	}
}

package br.com.constantino.enterprise.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {

	public static FacesContext getMessagem(FacesMessage.Severity severity, String mensagem, String mensagemDetalhada) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(severity, mensagem, mensagemDetalhada);
		context.addMessage(null, message);
		
		return context;
	}
}
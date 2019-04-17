package com.matheus.people.uteis;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class Uteis {
	public static EntityManager JPAEntityManager() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();

		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		return (EntityManager) request.getAttribute("entityManager");
	}

	/***
	 * Apresentar Mensagem de Alerta para o usuario
	 * 
	 * @param mensagem
	 */
	public static void Mensagem(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	/***
	 * Apresentar Mensagem de Atencao para o usuario
	 * 
	 * @param mensagem
	 */
	public static void MensagemAtencao(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}

	/***
	 * Apresentar Mensagem de Informacao para o usuario
	 * 
	 * @param mensagem
	 */
	public static void MensagemInfo(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

	/***
	 * Redirecionar para uma nova pagina
	 * Flash para manter as mensagens apos o redirecionamento
	 * 
	 * @param page
	 */
	public static void RedirectPage(String page) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.getExternalContext().getFlash().setKeepMessages(true);

		facesContext.getExternalContext().redirect(page);
	}
	
	public static void reload() throws IOException {
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}
}

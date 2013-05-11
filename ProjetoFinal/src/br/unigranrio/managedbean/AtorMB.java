package br.unigranrio.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;

import br.unigranrio.bean.requisito.Ator;
import br.unigranrio.bean.requisito.Projeto;
import br.unigranrio.controller.AtorController;
import br.unigranrio.dao.impl.AtorDAO;

@ManagedBean
@SessionScoped
public class AtorMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Ator ator = new Ator();
	private ListDataModel<Ator> atores = new ListDataModel<Ator>();
	private AtorDAO dao = new AtorDAO();
	private AtorController control = new AtorController();
	public Boolean cadastro = true;
	
	@ManagedProperty(value="#{projetoMB}")
	private ProjetoMB projetoMB;

	public String salvar() {
		Projeto projeto = projetoMB.getProjeto();
		ator.setProjeto(projeto);
		control.gravar(ator, projeto);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ator Salvo com Sucesso", ator.getNome()));
		ator = new Ator();
		return "listAtores";
	}
	
	public String atualizar(ActionEvent actionEvent){
		control.atualizar(ator);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ator Atualizado com Sucesso", ator.getNome()));
		return "listAtores";
	}
	
	public String remover(ActionEvent actionEvent){
		control.remover(ator.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ator Removido com Sucesso", ""));
		return "listAtores";
	}
	
	public void limpar(){
		ator = new Ator();
	}
	
	public void escolheAtor(ActionEvent actionEvent){
		ator = atores.getRowData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ator Escolhido: ", ator.getNome()));
	}

	public AtorMB() {
	}

	public ListDataModel<Ator> getAtores() {
		Projeto projeto = projetoMB.getProjeto();
		if(projeto.equals(null)){
			atores = new ListDataModel<Ator>();
		} else {
			long id = projeto.getId();
			atores = new ListDataModel<Ator>(dao.retornaPorProjeto(id));
		}
		return atores;
	}

	public void setAtores(ListDataModel<Ator> atores) {
		this.atores = atores;
	}

	public Ator getAtor() {
		return ator;
	}

	public void setAtor(Ator ator) {
		this.ator = ator;
	}

	public void setProjetoMB(ProjetoMB projetoMB){
		this.projetoMB = projetoMB;
	}
	
	public ProjetoMB getProjetoMB(){
		return projetoMB;
	}

}

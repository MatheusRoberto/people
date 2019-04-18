package com.matheus.people.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.matheus.people.DAO.PeopleDAO;
import com.matheus.people.entity.People;
import com.matheus.people.uteis.Uteis;

@Named(value = "peopleController")
@RequestScoped
public class PeopleController {

	@Inject
	private People people;

	@Inject
	private PeopleDAO peopleDAO;

	// List para dataTable
	private List<People> items;

	// Value para pesquisa
	private String name;
	// Value para pesquisa
	private String email;

	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		setItems(peopleDAO.listPeoples());
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public void setItems(List<People> items) {
		this.items = items;
	}

	public List<People> getItems() {
		return this.items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/***
	 * Salvar um novo cliente; Mensagem Info; reload para carregamento do list
	 */
	public void savePeople() {
		peopleDAO.save(this.people);

		this.people = null;

		Uteis.MensagemInfo("Cliente cadastrado com sucesso");

		try {
			Uteis.reload();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/***
	 * Deletar um cliente; Remove do list; Mensagem Info
	 * 
	 * @param p
	 */
	public void deletePeople(People p) {
		this.peopleDAO.delete(p.getIdPeople());

		this.getItems().remove(p);

		Uteis.MensagemInfo("Cliente removido com sucesso");
	}

	/***
	 * Primeira funcao para edicao do cliente setar o parametro como o valor people
	 * 
	 * @param p
	 */
	public void prepareEdit(People p) {
		this.setPeople(p);
	}

	/***
	 * Salvar atualizacao do cliente; Mensagem Info; Reload para carregamento do
	 * list
	 */
	public void updatePeople() {
		this.peopleDAO.update(this.people);

		this.init();

		Uteis.MensagemInfo("Cliente atualizado com sucesso");

		try {
			Uteis.reload();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * Funcao para buscar conforme os filtros passado nome e email; Funcao busca
	 * direto na list apos o construc;t Define a lista de items a lista filtrada
	 */
	public void filterPeople() {
		ArrayList<People> filtered = new ArrayList<People>();
		ArrayList<People> filterName = new ArrayList<People>(this.findByName());
		ArrayList<People> filterEmail = new ArrayList<People>(this.findByEmail());
		if (!filterName.isEmpty())
			filtered.addAll(filterName);
		if (!filterEmail.isEmpty())
			filtered.addAll(filterEmail);
		if (!filterEmail.isEmpty() || !filterName.isEmpty())
			setItems(filtered);
	}

	/***
	 * Limpar filtro Busca no banco todos os clientes novamente
	 */
	public void clearFilter() {
		setItems(peopleDAO.listPeoples());
	}

	/***
	 * Funcao para buscar cliente no List Items conforme o filtro de nome
	 * 
	 * @return filtered
	 */
	private List<People> findByName() {
		ArrayList<People> filtered = new ArrayList<People>();
		if (name.isEmpty())
			return new ArrayList<People>();

		for (People people : items) {
			if (people.getName().toLowerCase().contains(name.toLowerCase()))
				filtered.add(people);
		}
		return filtered;
	}

	/***
	 * Funcao para buscar cliente no List Items conforme o filtro de email
	 * 
	 * @return filtered
	 */
	private List<People> findByEmail() {
		ArrayList<People> filtered = new ArrayList<People>();
		if (email.isEmpty())
			return new ArrayList<People>();
		for (People people : items) {
			if (people.getEmail().toLowerCase().contains(email.toLowerCase()))
				filtered.add(people);
		}
		return filtered;
	}
}

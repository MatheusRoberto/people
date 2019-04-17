package com.matheus.people.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.matheus.people.entity.People;
import com.matheus.people.uteis.Uteis;

public class PeopleDAO {

	@Inject
	People people;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NONO CLIENTE
	 * 
	 * @param p
	 */
	public void save(People p) {
		this.entityManager = Uteis.JPAEntityManager();

		this.people = new People();
		this.people.setName(p.getName());
		this.people.setEmail(p.getEmail());
		this.people.setTelephone(p.getTelephone());

		this.entityManager.persist(this.people);
	}

	/***
	 * MÉTODO RESPONSÁVEL PARA LISTAR TODOS CLIENTES
	 */
	@SuppressWarnings("unchecked")
	public List<People> listPeoples() {
		this.entityManager = Uteis.JPAEntityManager();

		Query query = entityManager.createNamedQuery("People.findAll");

		List<People> peoples = new ArrayList<People>(query.getResultList());

		return peoples;
	}

	/***
	 * MÉTODO RESPONSÁVEL POR BUSCAR UM CLIENTE POR ID
	 * 
	 * @param id
	 */
	public People findPeople(int id) {
		entityManager = Uteis.JPAEntityManager();

		return entityManager.find(People.class, id);
	}

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR ATUALIZACAO DE UM CLIENTE
	 * 
	 * @param p
	 */
	public void update(People p) {
		entityManager = Uteis.JPAEntityManager();

		this.people = this.findPeople(p.getIdPeople());

		this.people.setEmail(p.getEmail());
		this.people.setName(p.getName());
		this.people.setTelephone(p.getTelephone());

		this.entityManager.merge(this.people);
	}

	/***
	 * MÉTODO RESPONSÁVEL POR EXCLUIR UM CLIENTE
	 * 
	 * @param id
	 */
	public void delete(int id) {
		entityManager = Uteis.JPAEntityManager();

		this.people = this.findPeople(id);

		entityManager.remove(this.people);
	}

	/***
	 * MÉTODO RESPONSÁVEL PARA LISTAR TODAS OS CLIENTES LIKE NAME
	 * @param name
	 */
	@SuppressWarnings("unchecked")
	public List<People> findByName(String name) {
		this.entityManager = Uteis.JPAEntityManager();

		Query query = entityManager.createNamedQuery("People.findByName").setParameter("name", "%" + name + "%");

		List<People> peoples = new ArrayList<People>(query.getResultList());

		return peoples;
	}

	/***
	 * MÉTODO RESPONSÁVEL PARA LISTAR TODOS OS CLIENTES LIKE EMAIL
	 * @param email
	 */
	@SuppressWarnings("unchecked")
	public List<People> findByEmail(String email) {
		this.entityManager = Uteis.JPAEntityManager();

		Query query = entityManager.createNamedQuery("People.findByEmail").setParameter("email", "%" + email + "%");

		List<People> peoples = new ArrayList<People>(query.getResultList());

		return peoples;
	}
	
	/***
	 * MÉTODO RESPONSÁVEL PARA LISTAR TODOS OS CLIENTES LIKE EMAIL AND NAME
	 * @param email
	 * @param name
	 */
	@SuppressWarnings("unchecked")
	public List<People> findByNameAndEmail(String name, String email) {
		this.entityManager = Uteis.JPAEntityManager();

		Query query = entityManager.createNamedQuery("People.findByNameAndEmail")
				.setParameter("email", "%" + email + "%")
				.setParameter("name", "%" + name + "%");

		List<People> peoples = new ArrayList<People>(query.getResultList());

		return peoples;
	}

}

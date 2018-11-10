package com.spring.sisfact.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.spring.sisfact.app.entity.Cliente;

@Repository("clienteDaoJPA")//Defimos un nombre si es que hubiera varias clases que implementan la misma interface
public class ClienteDaoImpl implements IClienteDao {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {
		
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void save(Cliente c) {
		if(c.getId()==null) {
			em.persist(c);
		}else 
			em.merge(c);
	}

	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}
	
	@Override
	public void delete(Long id) {
		Cliente c = this.findOne(id);
		em.remove(c);
	}
	
	
	

}

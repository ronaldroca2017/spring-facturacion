package com.spring.sisfact.app.dao;

import java.util.List;



import com.spring.sisfact.app.entity.Cliente;

public interface IClienteDao {
	
	public List<Cliente> findAll();
	
	public void save(Cliente c);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	

}

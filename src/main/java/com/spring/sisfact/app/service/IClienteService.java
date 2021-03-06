package com.spring.sisfact.app.service;

import java.util.List;

import com.spring.sisfact.app.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
	
	public void save(Cliente c);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
}

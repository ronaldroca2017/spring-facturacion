package com.spring.sisfact.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.sisfact.app.entity.Cliente;
import com.spring.sisfact.app.entity.Producto;

public interface IClienteService {
	public List<Cliente> findAll();
	
	public void save(Cliente c);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	
	public Page<Cliente> findAllPaginate(Pageable pageable);
	
	public List<Producto> findByNombre(String term);
}

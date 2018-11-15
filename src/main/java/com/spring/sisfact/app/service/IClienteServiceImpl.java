package com.spring.sisfact.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.sisfact.app.dao.IClienteDao;
import com.spring.sisfact.app.dao.IProductoDao;
import com.spring.sisfact.app.entity.Cliente;
import com.spring.sisfact.app.entity.Producto;

@Service
public class IClienteServiceImpl implements IClienteService {

	@Autowired
	//IClienteDao clienteDao;
	IClienteDao clienteDao;
	
	@Autowired 
	IProductoDao productoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
	  //return clienteDao.findAll();
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente c) {
		clienteDao.save(c);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(Long id) {
		return clienteDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.delete(id);
		
	}

	@Override
	public Page<Cliente> findAllPaginate(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	public List<Producto> findByNombre(String term) {
	
		//return productoDao.findByNombre(term);
		
		//return productoDao.findByNombreLikeIgnoreCase("%"+ term + "%");
		
		return productoDao.findByNombreContainingIgnoreCase(term);
	} 

}

package com.spring.sisfact.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.sisfact.app.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

	/*Autocomplete busqueda de productos usando like diferentes formas
	 * 
	 * */
	//Forma 1:
	@Query("select p from Producto p where p.nombre like %?1% ")
	public List<Producto> findByNombre(String term); 
	
	//Forma 2:
	public List<Producto> findByNombreLikeIgnoreCase(String term);
	
	
	//Forma 3:
	public List<Producto> findByNombreContainingIgnoreCase (String term);

	
}

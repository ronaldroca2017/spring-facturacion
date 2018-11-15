package com.spring.sisfact.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.sisfact.app.entity.Cliente;

//PagingAndSortingRepository internamente hereda la interface CrudRepository
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{

}

package com.spring.sisfact.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.sisfact.app.entity.Cliente;

public interface IClienteDaoCrudRepository extends CrudRepository<Cliente, Long>{

}

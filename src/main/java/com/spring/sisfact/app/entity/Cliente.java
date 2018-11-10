package com.spring.sisfact.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private String email;
	
	
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaCreacion;
	
	//mappedBy lo usamos cuando es bidireccional(Hay relacion en ambas clases)
	//mappedBy = "cliente" es para poder asociar le referencias entre las clases y las tablas ya que crea en la tabla factura un campo llamado cliente_id
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY ,  cascade = CascadeType.ALL)//Lazy sólo trae la consulta del objeto Cliente sin sus facturas
	private List<Factura> listaFactura;
	
	
	
	public Cliente() {
		listaFactura = new ArrayList<Factura>();
	}
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public List<Factura> getListaFactura() {
		return listaFactura;
	}



	public void setListaFactura(List<Factura> listaFactura) {
		this.listaFactura = listaFactura;
	}
	
	//Agregamos un objeto factura a la lista de facturas
	public void addFactura(Factura fact) {
		listaFactura.add(fact);
	}
	
	
	
}

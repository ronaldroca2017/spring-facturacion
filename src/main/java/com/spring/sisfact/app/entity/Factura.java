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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "facturas")
public class Factura {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer folio;
	
	private String descripcion;
	
	private String observacion;
	 
	@Column(name = "fecha_fact")
	@Temporal(TemporalType.DATE)
	private Date fechaFacturacion;
	
	@ManyToOne(fetch = FetchType.LAZY)//Lazy sólo trae la consulta del objeto Factura sin el cliente asociado
	private Cliente cliente;
	
	//@JoinColumn lo usamos cuando es unidireccional(Cuando no Hay relacion en ambas clases )
	//En este caso solo necesitamos de las facturas sus facturasItem y no viceversa
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id")//Definimos el nombre del campo que creará en la tabla factura_item
	private List<FacturaItem> facItem;
	

	public Factura() {
		facItem = new ArrayList<FacturaItem>();
				
	}

	@PrePersist
	public void fechaActual() {
		fechaFacturacion = new Date();
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public List<FacturaItem> getFacItem() {
		return facItem;
	}

	public void setFacItem(List<FacturaItem> facItem) {
		this.facItem = facItem;
	}

	
	//Agregamos un objeto facturaItem a la lista de facturasItem
	public void addFacturaItem(FacturaItem factitem) {
		this.facItem.add(factitem);
	}
	
	public double getGranTotal() {
		
		double suma = 0.0;
		
		for(int i=0; i<facItem.size() ; i++) {
			suma += facItem.get(i).calcularImporte();
		}
		
		return suma;
	}
	
	
	

}

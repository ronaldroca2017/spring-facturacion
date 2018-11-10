package com.spring.sisfact.app.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "factura_item")
public class FacturaItem {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int cantidad;
	
	//Un item de factura sólo puede tener un producto asi que tambien podriamos usar OneToOne
	@ManyToOne(fetch = FetchType.LAZY)//Crea la llave foreanea producto_id en la tabla factura_item
	//@JoinColumn(name = "producto_id") //Opcional porque sino ponemos @JoinColumn el lo genera automaticamente creando el campo producto_id
	private Producto producto;

	 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double calcularImporte() {
		return cantidad * producto.getPrecio();
	}
}

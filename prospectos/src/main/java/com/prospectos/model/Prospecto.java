package com.prospectos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "prospectos")
@Entity
public class Prospecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String nombre;
	@Column
	private String apellido_p;
	@Column
	private String apellido_m;
	@Column 
	private String calle;
	@Column
	private int numero;
	@Column
	private String colonia;
	@Column
	private String codigo_postal;
	@Column
	private String telefono;
	@Column
	private String rfc;
	@Column
	private String status;
	@Column
	private String observaciones;
	
	public Prospecto() {
		super();
	}
	
	
	public Prospecto(int id, String nombre, String apellido_p, String apellido_m, String calle, int numero,
			String colonia, String codigo_postal, String telefono, String rfc, String status, String observaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido_p = apellido_p;
		this.apellido_m = apellido_m;
		this.calle = calle;
		this.numero = numero;
		this.colonia = colonia;
		this.codigo_postal = codigo_postal;
		this.telefono = telefono;
		this.rfc = rfc;
		this.status = status;
		this.observaciones = observaciones;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_p() {
		return apellido_p;
	}

	public void setApellido_p(String apellido) {
		this.apellido_p = apellido;
	}

	public String getApellido_m() {
		return apellido_m;
	}

	public void setApellido_m(String apellido_m) {
		this.apellido_m = apellido_m;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	

}

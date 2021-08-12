package com.prospectos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "prospectosdocumentos")
@Entity
public class Documentos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public int id_documento;
	@Column
	public int id_prospecto;
	@Column(name = "documento", length = 100000000)
	public byte[] documento;
	@Column
	public String nombre;

	public Documentos() {

	}

	public Documentos(int id_documento, int id_prospecto, byte[] documento, String nombre) {
		super();
		this.id_documento = id_documento;
		this.id_prospecto = id_prospecto;
		this.documento = documento;
		this.nombre = nombre;
	}

	public int getId_documento() {
		return id_documento;
	}

	public void setId_documento(int id_documento) {
		this.id_documento = id_documento;
	}

	public int getId_prospecto() {
		return id_prospecto;
	}

	public void setId_prospecto(int id_prospecto) {
		this.id_prospecto = id_prospecto;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

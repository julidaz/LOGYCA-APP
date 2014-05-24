package com.logyca;

public class Experto {

	private String nombre;
	private String correo;
	private Long id;
	private String telefono;
	
	public Experto(Long idExperto, String nombre, String correo,
			String geoLocalizacion) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.id = idExperto;
		this.telefono = telefono;
	}
	
	public Experto(String nombre, String correo, String telefono) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}

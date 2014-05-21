package com.logyca;

public class Experto {

	private String nombre;
	private String correo;
	private int rating;
	private String telefono;
	
	public Experto(int idExperto, int idInteres, String nombre, String correo,
			String geoLocalizacion, int rating, String telefono) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.rating = rating;
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}

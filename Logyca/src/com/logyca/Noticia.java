package com.logyca;

public class Noticia {

	private String titulo;
	private String descripcion;
	private String link;
	
	public Noticia(String titulo, String descripcion, String link) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.link = link;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
}

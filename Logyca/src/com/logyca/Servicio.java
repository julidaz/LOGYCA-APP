package com.logyca;


import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by dacarden on 4/20/2014.
 */
public class Servicio {


    private String titulo;
    private String descripcion;
    private String link;

    private String tipo;
    private String fechaInicio;
    private String fechaFin;
    private String hora;
    private String duracion;
    private String direccion;
    private String ciudad;
    private String pais;
    private String encargado;

    public Servicio(String titulo, String descripcion, String link) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.link = link;
    }

    public Servicio(String titulo, String descripcion, String link, String tipo, String fechaInicio, String fechaFin, String hora, String duracion, String direccion, String ciudad, String pais, String encargado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.link = link;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hora = hora;
        this.duracion = duracion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.encargado = encargado;
        //System.out.println();
        //Log.d("logyca","Servicio: "+this.toString());
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", link='" + link + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", hora='" + hora + '\'' +
                ", duracion='" + duracion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", encargado='" + encargado + '\'' +
                '}';
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
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

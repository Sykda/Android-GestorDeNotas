package com.example.gestordenotas;

//Esta clase incluye los getter y setter para sacar la informacion de cada objeto de la bbdd.
public class Tarea {

    private int id, imagen;
    private String titulo, categoria, descripcion;

    public Tarea(int id, String titulo, String categoria, String descripcion, int imagen) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagen() {
        return imagen;
    }

}

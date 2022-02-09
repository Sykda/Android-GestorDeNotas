package com.example.gestordenotas;

public class Tarea {

    private int id;
    private String titulo, categoria, descripcion;

    public Tarea(int id, String titulo, String categoria, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public Tarea() {
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

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
}

package com.example.gestionmedicamentos;

public class Tipodocumento {
    String descripcion;



    public Tipodocumento() {
    }

    public Tipodocumento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

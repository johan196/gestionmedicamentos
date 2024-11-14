package com.example.gestionmedicamentos;

import androidx.recyclerview.widget.RecyclerView;

public class Tipodocumento {
    String descripcion;



    public Tipodocumento(int i, String tarjetaDeIdentidad) {
    }

    public Tipodocumento(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipodocumento() {

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "descripcion='" + descripcion + '}';
    }

}

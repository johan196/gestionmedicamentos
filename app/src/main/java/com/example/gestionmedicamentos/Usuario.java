package com.example.gestionmedicamentos;

public class Usuario{
    String nombrecompleto;
    int documento;
    String celular;
    String genero;
    String rol;
     private Tipodocumento tipodocumento;
     private Rol tiporol;
     private Genero sexo;

    public Usuario() {
    }

    public Usuario(String nombrecompleto, int documento, String celular, String genero, String rol, Tipodocumento tipodocumento, Rol tiporol, Genero sexo) {
        this.nombrecompleto = nombrecompleto;
        this.documento = documento;
        this.celular = celular;
        this.genero = genero;
        this.rol = rol;
        this.tipodocumento = tipodocumento;
        this.tiporol = tiporol;
        this.sexo = sexo;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Tipodocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(Tipodocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Rol getTiporol() {
        return tiporol;
    }

    public void setTiporol(Rol tiporol) {
        this.tiporol = tiporol;
    }

    public Genero getSexo() {
        return sexo;
    }

    public void setSexo(Genero sexo) {
        this.sexo = sexo;
    }
}


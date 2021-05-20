package com.albertlopez.cocinaparavagos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("IDUSUARIO")
    String idUsuario;
    @SerializedName("NICKNAME")
    String name;
    @SerializedName("PASS")
    String pass;
    @SerializedName("CORREOELECTRONICO")
    String email;

    public User() {}

    public User (String idUsuario, String name, String pass, String email) {
        this.idUsuario = idUsuario;
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

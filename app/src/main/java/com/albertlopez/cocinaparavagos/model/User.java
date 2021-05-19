package com.albertlopez.cocinaparavagos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("IDUSUARIO")
    String idUsuario;
    @SerializedName("NICKNAME")
    String name;
    @SerializedName("PASS")
    String email;
    @SerializedName("CORREOELECTRONICO")
    String pass;

    public User() {}

    public User (String name, String email, String pass, String id) {
        this.idUsuario = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
}

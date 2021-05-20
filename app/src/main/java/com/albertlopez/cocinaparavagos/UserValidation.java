package com.albertlopez.cocinaparavagos;


import com.albertlopez.cocinaparavagos.model.User;

public class UserValidation {

    static public User user = new User();
    static public Boolean validado = false;

    public static void setUser(User user) {
        UserValidation.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static void comprobarPass(String pass){
        if (user.getPass().equals(pass)) {
            validado = true;
        }
    }

    public static Boolean getValidado() {
        return validado;
    }

    public static void logout() {
        validado = false;
    }

}

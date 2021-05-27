package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.UserValidation;
import com.albertlopez.cocinaparavagos.model.User;
import com.google.gson.Gson;
import org.json.JSONException;
import java.io.Serializable;

public class ManagerUser implements Serializable {
    Gson gson = new Gson();
    User user = new User();

    public String encryptPass(String md5) {

        try{
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0X100).substring(1, 3));
            }
            return sb.toString();
        }catch (java.security.NoSuchAlgorithmException ignored){

        }
        return null;
    }

    public void addUsuario(String response) throws JSONException {
        user = gson.fromJson(response, User.class);
        UserValidation.setUser(user);
    }

}

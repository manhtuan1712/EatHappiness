package com.example.user.fragmenttablayout.Object;

/**
 * Created by ManhTuan on 9/20/2016.
 */
public class User {
    public String id;
    public String ten, matkhau, email;

    public User() {
    }

    public User(String id, String ten, String matkhau, String email) {
        this.id = id;
        this.ten = ten;
        this.matkhau = matkhau;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

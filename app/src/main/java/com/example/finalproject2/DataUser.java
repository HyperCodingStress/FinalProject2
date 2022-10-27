package com.example.finalproject2;

public class DataUser {
    public String nama,nomor,pass,email,role;

    public DataUser(){

    }

    public DataUser(String nama, String pass, String email, String role) {
        this.nama = nama;
        this.pass = pass;
        this.email = email;
        this.role = role;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DataUser(String nama, String nomor, String pass, String email, String role) {
        this.nama = nama;
        this.nomor = nomor;
        this.pass = pass;
        this.email = email;
        this.role = role;
    }
}

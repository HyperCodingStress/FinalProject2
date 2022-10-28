package com.example.finalproject2;

public class MainModel {
    String nama,url,stok;

    MainModel(){

    }
    public MainModel(String nama, String url, String stok) {
        this.nama = nama;
        this.url = url;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }
}

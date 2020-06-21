package com.example.androidmypos.Model;

import java.util.List;

public class ResponseModelU {
    private int kode;
    private String pesan;
    private List<UnitModel> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<UnitModel> getData() {
        return data;
    }

    public void setData(List<UnitModel> data) {
        this.data = data;
    }
}

package com.example.androidmypos.Model;

import java.util.List;

public class ResponseModelS {
    private int kode;
    private String pesan;
    private List<SupplierModel> data;

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

    public List<SupplierModel> getData() {
        return data;
    }

    public void setData(List<SupplierModel> data) {
        this.data = data;
    }
}

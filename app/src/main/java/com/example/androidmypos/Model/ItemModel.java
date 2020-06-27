package com.example.androidmypos.Model;

public class ItemModel {
    private int item_id;
    private String barcode, name, category_id, unit_id, price, berat, deskripsi, stock;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory_id(String category_id) { this.category_id = category_id;}
    public String getCategory_id() { return category_id; }

    public void setUnit_id(String unit_id) { this.unit_id = unit_id;}
    public String getUnit_id() { return unit_id; }

    public void setPrice(String price) { this.price = price;}
    public String getPrice() { return price; }

    public void setBerat(String berat) { this.berat = berat;}
    public String getBerat() { return berat; }

    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi;}
    public String getDeskripsi() { return deskripsi; }

    public String getStock() {
        return stock;
    }
    public void setStock(String stock) {
        this.stock = stock;
    }


}
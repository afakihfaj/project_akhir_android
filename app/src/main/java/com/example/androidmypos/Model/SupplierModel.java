package com.example.androidmypos.Model;

public class SupplierModel {
    private int supplier_id;
    private String name, phone, address, description;

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) { this.phone = phone;}
    public String getPhone() { return phone; }

    public void setAddress(String address) { this.address = address;}
    public String getAddress() { return address; }

    public void setDescription(String description) { this.address = description;}
    public String getDescription() { return phone; }


}

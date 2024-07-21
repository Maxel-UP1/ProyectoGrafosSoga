package model;

import java.util.ArrayList;

public class Owner {
    private String name;
    private String passString;
    private String rol;
    private String email;
    private String address;
    private String phone;
    private int id;
    private ArrayList<Package> packages;

    public Owner(String name, String passString, String rol, String email, String address, String phone, int id,
            ArrayList<Package> packages) {
        this.name = name;
        this.passString = passString;
        this.rol = rol;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.id = id;
        this.packages = packages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassString() {
        return passString;
    }

    public void setPassString(String passString) {
        this.passString = passString;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

    @Override
    public String toString() {
        return "Owner [name=" + name + ", passString=" + passString + ", rol=" + rol + ", email=" + email + ", address="
                + address + ", phone=" + phone + ", id=" + id + ", packages=" + packages + "]";
    }

    public void addPackage(String name, String address, Owner deliverMan, String status) {
        Package newPackage = new Package(name, address, this, deliverMan, status);
        packages.add(newPackage);
    }

}

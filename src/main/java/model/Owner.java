package model;

import java.util.ArrayList;

public class Owner {
    private String name;
    private String password;
    private String rol;
    private String email;
    private String address;
    private String phone;
    private int id;
    private ArrayList<Package> packages;

    public Owner(String name, String passString, String rol, String email, String address, String phone, int id,
            ArrayList<Package> packages) {
        this.name = name;
        this.password = passString;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}

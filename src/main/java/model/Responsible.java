package model;

import java.util.ArrayList;

public class Responsible {
    private String name;
    private String passString;
    private String rol;
    private String email;
    private ArrayList<Package> packages;

    public Responsible(String name, String passString, String rol, String email, ArrayList<Package> packages) {
        this.name = name;
        this.passString = passString;
        this.rol = rol;
        this.email = email;
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

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

}

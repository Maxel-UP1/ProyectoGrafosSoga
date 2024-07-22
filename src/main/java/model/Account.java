package model;

import java.util.ArrayList;

public class Account {
    String idUser;
    String nameAcount;
    String password;
    private Role rol;
    private ArrayList<String> packagesIds;

    public Account(String idUser, String nameAcount, String password, Role rol) {
        this.idUser = idUser;
        this.nameAcount = nameAcount;
        this.password = password;
        this.rol = rol;
        this.packagesIds = new ArrayList<>();
    }

    public void addPackageId(String packageId) {
        packagesIds.add(packageId);
    }

    public String getNameAcount() {
        return nameAcount;
    }

    public void setNameAcount(String nameAcount) {
        this.nameAcount = nameAcount;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public ArrayList<String> getPackagesIds() {
        return packagesIds;
    }

    public void setPackagesIds(ArrayList<String> packagesIds) {
        this.packagesIds = packagesIds;
    }

    @Override
    public String toString() {
return "Account{" +
                "idUser='" + idUser + '\'' +
                ", nameAcount='" + nameAcount + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                ", packagesIds=" + packagesIds +
                '}';
    }
}


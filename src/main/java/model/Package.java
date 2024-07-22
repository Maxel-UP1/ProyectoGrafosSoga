package model;

import utilities.Utilities;

public class Package {
    private String id;
    private String name;
    private String address;
    private String status;
    private Utilities utilities;

    public Package(String name, String address, String status) {
        this.id = Utilities.generateId();
        this.name = name;
        this.address = address;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", utilities=" + utilities +
                '}';
    }
}

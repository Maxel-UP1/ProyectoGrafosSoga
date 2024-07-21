package model;

import utilities.Utilities;

public class Package {
    private String name;
    private String address;
    private Owner owner;
    private Owner deliverMan;
    private String id;
    private String status;

    public Package(String name, String address, Owner owner, Owner deliverMan, String status) {
        this.name = name;
        this.address = address;
        this.owner = owner;
        this.deliverMan = deliverMan;
        this.id = Utilities.generateId();
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getDeliverMan() {
        return deliverMan;
    }

    @Override
    public String toString() {
        return "Package [name=" + name + ", address=" + address + ", owner=" + owner + ", deliverMan=" + deliverMan
                + ", id=" + id + ", status=" + status + "]";
    }

}

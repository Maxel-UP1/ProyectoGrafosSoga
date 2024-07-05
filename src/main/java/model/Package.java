package model;

public class Package {
    private String name;
    private String address;
    private Owner owner;
    private int id;

    public Package(String name, String address, Owner owner, int id) {
        this.name = name;
        this.address = address;
        this.owner = owner;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}

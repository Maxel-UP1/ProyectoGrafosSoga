package model;

public class Owner {
    private String name;
    private String email;
    private String address;
    private String phone;
    private int id;

    public Owner(String name, String email, String address, String phone, int id) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

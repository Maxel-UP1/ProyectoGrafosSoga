package controlers;

import model.Owner;

import java.util.ArrayList;

public class LoginController {

    private ArrayList<Owner> ownersList;
    private Owner ownerLogged;

    public LoginController() {
        this.ownersList = new ArrayList<>();
        //LLamar a la persistencia de los usuarios  y ponerselo en el igual!!!
    }

    public boolean login(String nameUser, String password) {
        for (Owner owner : ownersList) {
            if(owner.getName().equals(nameUser) && owner.getPassword().equals(password)) {
                ownerLogged = owner;
                return true;
            }
        }
        return false;
    }



}

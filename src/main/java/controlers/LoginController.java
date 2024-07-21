package controlers;

import model.Owner;

import java.util.ArrayList;

public class LoginController {
    private OwnerAccountController ownerAccountController;
    private ArrayList<Owner> ownersList;
    private Owner ownerLogged;

    public Owner getOwnerLogged() {
        return ownerLogged;
    }


    public LoginController(OwnerAccountController ownerAccountController) {
        this.ownerAccountController = ownerAccountController;
        this.ownersList = ownerAccountController.getOwnersList();

        //LLamar a la persistencia de los usuarios  y ponerselo en el igual!!!
    }

    public boolean login(String nameAcountUser, String password) {
        ownersList = ownerAccountController.getOwnersList();
        for (Owner owner : ownersList) {
            if (owner.getNameAcount().equals(nameAcountUser) && owner.getPassword().equals(password)) {
                ownerLogged = owner;
                return true;
            }
        }
        return false;
    }




}

package controlers;

import com.google.gson.reflect.TypeToken;
import model.Owner;
import model.Package;
import utilities.JsonStorageUtilities;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OwnerAccountController {

    private ArrayList<Owner> ownersList;
    private final JsonStorageUtilities jsonStorageUtilities;
    private static final Type PERSONTYPE = new TypeToken<List<Owner>>() {
    }.getType();

    public OwnerAccountController() {
        this.ownersList = new ArrayList<>();
        this.jsonStorageUtilities = new JsonStorageUtilities();
    }

    public void chargeOwnersReadFile(String nameFile) {

        List<Owner> userList = jsonStorageUtilities.readContentFromFile(nameFile, PERSONTYPE);
        this.ownersList = new ArrayList<>(userList);

    }

    public ArrayList<Owner> getOwnersList() {

        return ownersList;
    }

    public Owner firtsDeliveredMan() {
        for (Owner owner : ownersList) {
            if (owner.getRol().equals("DELIVER")) {
                return owner;
            }
        }
        return null;
    }

    public void addPack(Owner owner, Owner deliverMan, String name, String address, String status) {
        // owner.addPackage(name, address, deliverMan, status);
        Package newPackage = new Package(name, address, owner, deliverMan, status);
        // agregar nuevos paquetes a los dueños
        // owner.addPackagE2(newPackage);
        // deliverMan.addPackagE2(newPackage);
        // jsonStorageUtilities.saveDataToFile(ownersList, "owners", PERSONTYPE);
    }

}

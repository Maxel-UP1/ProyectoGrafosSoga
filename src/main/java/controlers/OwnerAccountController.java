package controlers;

import com.google.gson.reflect.TypeToken;
import model.Owner;
import utilities.JsonStorageUtilities;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OwnerAccountController {

    private ArrayList<Owner> ownersList;
    private final JsonStorageUtilities jsonStorageUtilities;
    private static  final Type PERSONTYPE = new TypeToken<List<Owner>>(){}.getType();

    public OwnerAccountController() {
        this.ownersList = new ArrayList<>();
        this.jsonStorageUtilities = new JsonStorageUtilities();
    }

    public void chargeOwnersReadFile(String nameFile) {

        List<Owner> userList   = jsonStorageUtilities.readContentFromFile(nameFile, PERSONTYPE);
        this.ownersList = new ArrayList<>(userList);

    }

    public ArrayList<Owner> getOwnersList() {

        return ownersList;
    }


}

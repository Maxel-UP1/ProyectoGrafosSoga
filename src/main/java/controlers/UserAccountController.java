package controlers;

import com.google.gson.reflect.TypeToken;
import model.Account;
import model.Package;
import model.User;
import utilities.JsonStorageUtilities;
import utilities.Utilities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserAccountController {
    private Utilities accUtilities;
    private ArrayList<User> usersList;
    private ArrayList<Account> acountsList;
    private  ArrayList<Package> packagesList;
    private JsonStorageUtilities jsonStorageUtilities;
    private static final Type PACKAGETYPE = new TypeToken<List<Package>>() {
    }.getType();
    private static final Type PERSONTYPE = new TypeToken<List<User>>() {
    }.getType();


    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public ArrayList<Account> getAcountsList() {
        return acountsList;
    }

    public void setAcountsList(ArrayList<Account> acountsList) {
        this.acountsList = acountsList;
    }




    public ArrayList<User> getUsers() {
        return usersList;
    }


    public ArrayList<Account> getAcounts() {
        return acountsList;
    }



    public UserAccountController() {
        this.accUtilities = new Utilities();
        this.usersList = new ArrayList<>();
        this.acountsList = new ArrayList<>();
        this.packagesList = new ArrayList<>();
        this.jsonStorageUtilities = new JsonStorageUtilities();
    }

    public Account firtsDeliveredMan() {
        for (User user : usersList) {
            if (user.getAccount().getRol().name().equals("DELIVER")) {
                return user.getAccount();
            }
        }
        return null;
    }

    public void addPack(Account owner, Account deliver, String namePack, String adress, String status){
        Package pack = new Package(namePack, adress, status);
        owner.addPackageId(pack.getId());
        deliver.addPackageId(pack.getId());
        packagesList.add(pack);
        for (User  user : usersList){
            if (user.getAccount().getIdUser().equals(owner.getIdUser())){
                writeFileAlone("users");
            }
            if (user.getAccount().getIdUser().equals(deliver.getIdUser())){
                writeFileAlone("users");
            }
        }
        writeFileAlonePackage("packages");


    }
    public void cancelPack(String idPack){
        for (Package pack : packagesList){
            if (pack.getId().equals(idPack)){
                pack.setStatus("CANCELED");

                for (User user : usersList){
                    if (user.getAccount().getPackagesIds().contains(idPack)){
                        user.getAccount().getPackagesIds().remove(idPack);
                        writeFileAlone("users");
                    }
                }
                pack.setId("CANCELED");
            }
        }
        writeFileAlonePackage("packages");
}


    //Metodos para persistencia
    public void chargeUsersReadFile(String nameFile) {

        List<User> userList = jsonStorageUtilities.readContentFromFile(nameFile, PERSONTYPE);

        //Convertir la lista de usuarios a un ArrayList<User>
        this.usersList = new ArrayList<>(userList);
        //gurada las cuentas en su lista
        for (User user : usersList) {
            acountsList.add(user.getAccount());
            //System.out.println(user.toString() + "\n cuenta " + user.getAcount().toString());
        }

    }

    public void writeFileReading(String nameFile, User user) {
        List<User> userListTEMP = jsonStorageUtilities.readContentFromFile(nameFile, PERSONTYPE);
        userListTEMP.add(user);
        jsonStorageUtilities.saveDataToFile(userListTEMP, nameFile, PERSONTYPE);
        //se llama al metodo para recargar las cuentas en su almacenamientos.
        chargeUsersReadFile(nameFile);

    }


    public void chargePackagesReadFile(String nameFile) {

        List<Package> packageList = jsonStorageUtilities.readContentFromFile(nameFile, PACKAGETYPE);
        if (packageList != null) {
            this.packagesList = new ArrayList<>(packageList);
        }

    }

    public void writeFileAlonePackage(String nameFile) {
        jsonStorageUtilities.saveDataToFile(packagesList, nameFile, PACKAGETYPE);
    }
    public void writeFileAlone(String nameFile) {
        jsonStorageUtilities.saveDataToFile(usersList, nameFile, PERSONTYPE);
    }
}



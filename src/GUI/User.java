package GUI;

import java.io.Serializable;
import java.util.List;

abstract class User implements Serializable {
    protected String username;
    protected String password;
    protected String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    // Getters and setters...
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void deleteBagById(BagShop shop, String id) {
        shop.removeBagById(id);
    }

    public void searchBagsByCategory(BagShop shop, String category) {
        shop.searchBagsByCategory(category);
    }

    public void addBag(BagShop shop, Bag bag) {
        shop.addBag(bag);
    }
}

class Cashier extends User {
    public Cashier(String username, String password) {
        super(username, password, "Cashier");
    }

    public void addBag(BagShop shop, Bag bag) {
        shop.addBag(bag);
    }

    public void deleteBagById(BagShop shop, String id) {
        shop.removeBagById(id);
    }
}

class Manager extends Cashier {
    public Manager(String username, String password) {
        super(username, password);
        this.role = "Manager";
    }

    public void createUser(BagShop shop, String username, String password) {
        Cashier newCashier = new Cashier(username, password);
        shop.addUser(newCashier);
    }

    public List<User> viewCashiers(BagShop shop) {
        return shop.getCashiers();
    }
}

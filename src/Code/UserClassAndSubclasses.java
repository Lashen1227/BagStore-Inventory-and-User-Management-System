package Code;

import java.io.Serializable;
import java.util.List;

// Code.User class is an abstract class that represents a user in the system
abstract class User implements Serializable {
    protected String username;
    protected String password;
    protected String role;

    // Constructor to initialize the Code.User object
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters to access the private fields
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    // Setters to modify the private fields
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Method to authenticate the user
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    // Override the toString method to return the Code.User object as a string
    @Override
    public String toString() {
        return "User[Username=" + username + ", Role=" + role + "]";
    }
    // Method to add a bag to the shop
    public void addBag(BagShop shop, Bag newBag) {
        if (this instanceof Cashier) {
            ((Cashier) this).addBag(shop, newBag);
        } else {
            System.out.println("You are not authorized to add a bag.");
        }
    }
}


// Code.Cashier class is a subclass of Code.User class
class Cashier extends User {
    public Cashier(String username, String password) {
        super(username, password, "Cashier");
    }

    public void viewBags(BagShop shop) {
        List<Bag> bags = shop.getBags();
        if (bags.isEmpty()) {
            System.out.println("No bags added yet.");
        } else {
            for (Bag bag : bags) {
                System.out.println(bag);
            }
        }
    }

    public void addBag(BagShop shop, Bag bag) {
        shop.addBag(bag);
    }

    public void deleteBagById(BagShop shop, String id) {
        boolean removed = shop.removeBagById(id);
        if (removed) {
            System.out.println("Bag with ID " + id + " has been deleted.");
        } else {
            System.out.println("No bag found with ID " + id);
        }
    }

    public void searchBagsByCategory(BagShop shop, String category) {
        List<Bag> bags = shop.getBagsByCategory(category);
        if (bags.isEmpty()) {
            System.out.println("No bags found in category: " + category);
        } else {
            for (Bag bag : bags) {
                System.out.println(bag);
            }
        }
    }
}


// Code.Manager class is a subclass of Code.Cashier class
class Manager extends Cashier {
    // Constructor to initialize the Code.Manager object
    public Manager(String username, String password) {
        super(username, password);
        this.role = "Manager";
    }

    // Override the addBag method to allow the manager to add a bag
    public void createUser(BagShop shop, String username, String password) {
        User newUser = new Cashier(username, password);
        shop.addUser(newUser);
    }

    // Override the viewBags method to allow the manager to view bags
    public void viewCashiers(BagShop shop) {
        List<User> cashiers = shop.getCashiers();
        if (cashiers.isEmpty()) {
            System.out.println("No cashiers added yet.");
        } else {
            for (User user : cashiers) {
                System.out.println("Cashier[Username=" + user.getUsername() + ", Password=" + user.getPassword() + "]");
            }
        }
    }
}


package GUI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class BagShop {
    private List<Bag> bags;
    private List<User> users;
    private final String BAG_FILE = "BagGUI.ser";
    private final String USER_FILE = "UserGUI.ser";

    public BagShop() {
        this.bags = new ArrayList<>();
        this.users = new ArrayList<>();
        loadBagsFromFile();
        loadUsersFromFile();
    }

    public void addBag(Bag bag) {
        bags.add(bag);
        saveBagsToFile();
    }

    public boolean removeBagById(String id) {
        for (Bag bag : bags) {
            if (bag.getId().equals(id)) {
                bags.remove(bag);
                saveBagsToFile();
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersToFile();
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                return user;
            }
        }
        return null;
    }

    private void loadBagsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BAG_FILE))) {
            bags = (List<Bag>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No bags loaded from file.");
        }
    }

    private void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No users loaded from file.");
        }
    }

    private void saveBagsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BAG_FILE))) {
            oos.writeObject(bags);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Bag> getBags() {
        return bags;
    }

    public void searchBagsByCategory(String category) {
        List<Bag> foundBags = new ArrayList<>();
        for (Bag bag : bags) {
            if (bag.getCategory().equalsIgnoreCase(category)) {
                foundBags.add(bag);
            }
        }
        if (foundBags.isEmpty()) {
            System.out.println("No bags found in category: " + category);
        } else {
            System.out.println("Bags in category " + category + ":");
            for (Bag bag : foundBags) {
                System.out.println(bag);
            }
        }
    }

    public List<User> getCashiers() {
        List<User> cashiers = new ArrayList<>();
        for (User user : users) {
            if (user.getRole().equals("Cashier")) {
                cashiers.add(user);
            }
        }
        return cashiers;
    }

    public boolean isBagIdUnique(String id) {
        for (Bag bag : bags) {
            if (bag.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
}

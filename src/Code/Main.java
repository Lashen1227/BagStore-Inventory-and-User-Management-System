package Code;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n---- Welcome to The Little Bag Shop System! ----");
        BagShop shop = new BagShop();
        Scanner scanner = new Scanner(System.in);

        // Sample Code.Manager Code.User Account
        Manager manager = new Manager("admin", "admin");
        shop.addUser(manager);

        while (true) {
            try {
                System.out.println("\n*********************");
                System.out.println("***** Main Menu *****");
                System.out.println("*********************");
                System.out.println("1. Login as Cashier");
                System.out.println("2. Login as Manager");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("\nCashier Login:- ");  // Code.Cashier Login
                        System.out.print("Enter username: ");
                        String cashierUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String cashierPassword = scanner.nextLine();

                        User cashierUser = shop.authenticateUser(cashierUsername, cashierPassword);

                        if (cashierUser != null && cashierUser instanceof Cashier) {
                            System.out.println("Logged in as Code.Cashier.");
                            System.out.println("Welcome, " + cashierUser.getUsername() + " to the " + cashierUser.getRole() + " Page!");
                            cashierOptions(scanner, (Cashier) cashierUser, shop);
                        } else {
                            System.out.println("Invalid credentials or you are not authorized as Cashier. Try again.");
                        }
                        break;
                    case 2:
                        System.out.println("\nManager Login:- ");  // Code.Manager Login
                        System.out.print("Enter username: ");
                        String managerUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String managerPassword = scanner.nextLine();

                        User managerUser = shop.authenticateUser(managerUsername, managerPassword);

                        if (managerUser != null && managerUser instanceof Manager) {
                            System.out.println("Logged in as Manager.");
                            managerOptions(scanner, (Manager) managerUser, shop);
                        } else {
                            System.out.println("Invalid credentials or you are not authorized as Manager. Try again.");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting the system... Goodbye!");  // Exit
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Code.Cashier Options Menu
     * @param scanner Scanner object to read input
     * @param cashier Code.Cashier object to perform actions
     * @param shop Code.BagShop object to access data
     */
    private static void cashierOptions(Scanner scanner, Cashier cashier, BagShop shop) {
        while (true) {
            try {
                System.out.println("\n**********************");
                System.out.println("**** Cashier Menu ****");
                System.out.println("**********************");
                System.out.println("1. View Bags");
                System.out.println("2. Add Code.Bag");
                System.out.println("3. Delete Bag by ID");
                System.out.println("4. Search Bags by Category");
                System.out.println("5. Logout");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scanner.nextLine());  // Read the choice from the user

                switch (choice) {
                    case 1:
                        System.out.println("\nBag Details:");
                        cashier.viewBags(shop);
                        break;
                    case 2:
                        addBagWithUniqueID(scanner, cashier, shop);
                        break;
                    case 3:
                        System.out.print("Enter Bag ID to Delete: ");
                        String deleteId = scanner.nextLine();
                        cashier.deleteBagById(shop, deleteId);
                        break;
                    case 4:
                        System.out.print("Enter Category to Search: ");
                        String searchCategory = scanner.nextLine();
                        cashier.searchBagsByCategory(shop, searchCategory);
                        break;
                    case 5:
                        System.out.println("Logged out Cashier Page.");
                        return; // Log out
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input. Please enter the correct data.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Code.Manager Options Menu
     * @param scanner Scanner object to read input
     * @param manager Code.Manager object to perform actions
     * @param shop Code.BagShop object to access data
     */
    private static void managerOptions(Scanner scanner, Manager manager, BagShop shop) {
        while (true) {
            try {
                System.out.println("\n******************************");
                System.out.println("******** Manager Menu ********");
                System.out.println("******************************");
                System.out.println("1. View Bags");
                System.out.println("2. Add Bag");
                System.out.println("3. Delete Bag by ID");
                System.out.println("4. Search Bags by Category");
                System.out.println("5. Create New Cashier Account");
                System.out.println("6. View All Cashier Accounts");
                System.out.println("7. Logout");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scanner.nextLine());  // Read the choice from the user

                switch (choice) {
                    case 1:
                        System.out.println("\nBag Details:");
                        manager.viewBags(shop);
                        break;
                    case 2:
                        addBagWithUniqueID(scanner, manager, shop);
                        break;
                    case 3:
                        System.out.print("Enter Bag ID to Delete: ");
                        String deleteId = scanner.nextLine();
                        manager.deleteBagById(shop, deleteId);
                        break;
                    case 4:
                        System.out.print("Enter Category to Search: ");
                        String searchCategory = scanner.nextLine();
                        manager.searchBagsByCategory(shop, searchCategory);
                        break;
                    case 5:
                        System.out.print("\nEnter New Cashier Username: ");
                        String newUsername = scanner.nextLine();
                        System.out.print("Enter New Cashier Password: ");
                        String newPassword = scanner.nextLine();
                        manager.createUser(shop, newUsername, newPassword);
                        System.out.println("New cashier account created successfully.");
                        break;
                    case 6:
                        System.out.println("\nCashier Details: ");
                        manager.viewCashiers(shop);
                        break;
                    case 7:
                        System.out.println("Logged out Manager Page.");
                        return; // Log out
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input. Please enter the correct data.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Add a Code.Bag with a Unique ID
     * @param scanner Scanner object to read input
     * @param user Code.User object to add the bag
     * @param shop Code.BagShop object to check for unique ID
     */
    private static void addBagWithUniqueID(Scanner scanner, User user, BagShop shop) {
        System.out.println("\nAdd a New Bag:- ");
        while (true) {
            System.out.print("Enter Bag ID: ");
            String id = scanner.nextLine();

            if (shop.isBagIdUnique(id)) {
                System.out.print("Enter Bag Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Bag Category: ");
                String category = scanner.nextLine();
                System.out.print("Enter Bag Colour: ");
                String colour = scanner.nextLine();
                System.out.print("Enter Bag Price: ");
                double price = Double.parseDouble(scanner.nextLine());  // Read the price from the user

                Bag newBag = new Bag(id, name, category, colour, price);
                user.addBag(shop, newBag);
                System.out.println("Bag added successfully.");
                break;
            } else {
                System.out.println(".Bag ID already exists. Please enter a different Code.Bag ID.");
            }
        }
    }
}


/* References:
- https://www.programiz.com/java-programming/exception-handling
- https://www.programiz.com/java-programming/inheritance
- https://www.w3schools.com/java/java_oop.asp
- https://javatpoint.com/java-oops-concepts
- http://www.javapractices.com/topic/TopicAction.do?Id=70 (Serialization and subclassing)
- https://www.geeksforgeeks.org/object-serialization-inheritance-java/ (Object Serialization with Inheritance)
*/


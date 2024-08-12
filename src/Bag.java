import java.io.Serializable;

// Bag class is a serializable class that represents a bag in the system
class Bag implements Serializable {
    private String id;
    private String name;
    private String category;
    private String colour;
    private double price;


    // Constructor to initialize the Bag object
    public Bag(String id, String name, String category, String colour, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.colour = colour;
        this.price = price;
    }

    // Getters to access the private fields
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getColour() { return colour; }
    public double getPrice() { return price; }

    // Setters to modify the private fields
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Override the toString method to return the Bag object as a string
    @Override
    public String toString() {
        return "Bag[ID=" + id + ", Name=" + name + ", Category=" + category + ", Colour=" + colour + ", Price=" + price + "]";
    }
}


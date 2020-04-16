package id.ac.binus.myinventory;

public class Item {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Item(String name, String description, String quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Item(int id, String name, String description, String quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    private int id;
    private String name;
    private String description;
    private String quantity;

}

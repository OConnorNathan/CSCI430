package ProjectOne.src;

import java.io.*;

public class Shipment implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SHIPMENT_STRING = "S";
    private String id, products, quantity, price;

    // constructor
    Shipment(String products, String quantity, String price) {
        id = SHIPMENT_STRING + (ShipmentIdServer.instance()).getId();
        this.products = products;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getProducts() {
        return products;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // converting Shipment to string ouput
    public String toString() {
        return "ID: " + id + " Product Name: " + products + " Quantity: " + quantity + " Price: " + price;
    }
}

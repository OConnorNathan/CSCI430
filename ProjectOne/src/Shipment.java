/*******************************************************************
 * 
 * Project 1: Warehouse, Inventory implementation
 * File: Product.java
 * 
 * Author: Jacob Haapoja
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Book.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/
package ProjectOne.src;

import java.io.*;

public class Shipment implements Serializable {
    private static final long serialVersionUID = 1L;
    private int shipmentID;
    private int productID;
    private int quantity;


    // constructor
    Shipment(int productID, int quantity) {
        this.shipmentID = (ShipmentIdServer.instance()).getId();
        this.quantity = quantity;
        this.productID = productID;
    }

    public int getShipmentId() {
        return shipmentID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductId(int id) {
        this.productID = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // converting Shipment to string ouput
    public String toString() {
        return "ShipmentID: " + shipmentID + "ProductID: " + productID + "Quantity: " + quantity;
    }
}

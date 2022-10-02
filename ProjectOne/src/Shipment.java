/*******************************************************************
 * 
 * Project 1: Warehouse, Inventory implementation
 * File: Product.java
 * 
 * Author: Blake Hoosline
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Catalog.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/
//package ProjectOne.src;

import java.io.*;
import java.util.*;

public class Shipment implements Serializable {
    private static final long serialVersionUID = 1L;
    private int shipmentID;
    private int productID;
    private int quantity;
    private double price;
    private ShipmentHistory shipmentHist = new ShipmentHistory();

    // constructor
    public Shipment(int productID, int quantity, double price) {
        this.shipmentID = (ShipmentIdServer.instance()).getId();
        this.quantity = quantity;
        this.productID = productID;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setProductId(int id) {
        this.productID = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // public double makePayment(double payment) {
    //     return shipmentHist.makePayment(payment);
    // }

    // converting Shipment to string ouput
    public String toString() {
        // String string = shipmentID + " " + productID + " " + shipmentHist.toString();
        return " | ShipmentID: " + shipmentID + " | ProductID: " + productID + " | Quantity: " + quantity + " | Price: "
                + price; 
                // + " | " + shipmentHist.toString();
    }

}
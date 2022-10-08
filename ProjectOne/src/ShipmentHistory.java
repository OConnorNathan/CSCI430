
/*******************************************************************
 * 
 * Project 1: Warehouse, ShipmentHistory implementation
 * File: ShipmentHistory.java
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

import java.io.Serializable;
import java.util.*;

public class ShipmentHistory implements Serializable {
    private LinkedList<Shipment> shipments;
    //private double payment;

    public ShipmentHistory(double payment, LinkedList<Shipment> shipments) {
        this.shipments = shipments;
        //this.payment = payment;
    }

    public ShipmentHistory() {
        shipments = new LinkedList<Shipment>();
        //payment = 0;
    }

    public Iterator<Shipment> getShipments() {
        return shipments.iterator();
    }

    // public double makePayment(double balance) {
    //     payment = payment + balance;
    //     return payment;
    // }

    // public String toString() {
    //     return "Payment: " + payment;
    // }
}
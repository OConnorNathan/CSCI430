/*******************************************************************
 * 
 * Project 1: Warehouse, ShipmentList implementation
 * File: ShipmentList.java
 * 
 * Author: Blake Hoosline
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Catalog.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/
package ProjectOne.src;

import java.util.*;

public class ShipmentList {

    private LinkedList<Shipment> shipments;

    public ShipmentList() {
        shipments = new LinkedList<Shipment>();
    }

    public boolean addShipment(Shipment shipment) {
        return shipments.add(shipment);
    }

    public boolean receiveShipment(Shipment shipment) {
        return shipments.add(shipment);
    }

    public boolean makePayment(Shipment shipment) {
        return shipments.add(shipment);
    }

    public Iterator getShipments() {
        return shipments.iterator();
    }
}

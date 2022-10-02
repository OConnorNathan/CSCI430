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
//package ProjectOne.src;

import java.util.*;
import java.io.*;

public class ShipmentList implements Serializable {
    private static final long serialVersionUID = 1L;
    private LinkedList<Shipment> shipments = new LinkedList<Shipment>();
    private static ShipmentList shipmentList;

    private ShipmentList() {
    }

    public static ShipmentList instance() {
        if (shipmentList == null) {
            return (shipmentList = new ShipmentList());
        } else {
            return shipmentList;
        }
    }

    public boolean insertShipment(Shipment shipment) {
        return shipments.add(shipment);
    }

    public Iterator<Shipment> getShipments() {
        return shipments.iterator();
    }

    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(shipmentList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void readObject(java.io.ObjectInputStream input) {
        try {
            if (shipmentList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (shipmentList == null) {
                    shipmentList = (ShipmentList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    public String toString() {
        return shipments.toString();
    }
}
/*******************************************************************
 * 
 * Project 1: Warehouse, ShipmentIdServer implementation
 * File: ShipmentIdServer.java
 * 
 * Author: Blake Hoosline
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: MemberIdServer.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/
//package ProjectOne.src;

import java.io.*;

public class ShipmentIdServer implements Serializable {
    private int idCounter;
    private static ShipmentIdServer shipmentServer;

    // constructor
    private ShipmentIdServer() {
        idCounter = 1;
    }

    // creating instance for ShipmentIdServer
    public static ShipmentIdServer instance() {
        if (shipmentServer == null) {
            return (shipmentServer = new ShipmentIdServer());
        } else {
            return shipmentServer;
        }
    }

    // gets shipment id counter
    public int getId() {
        return idCounter++;
    }

    // converts ShipmentIdServer to a string output
    public String toString() {
        return ("IdServer" + idCounter);
    }

    // retrieves data from disk to get counter
    public static void retrieve(ObjectInputStream input) {
        try {
            shipmentServer = (ShipmentIdServer) input.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    // writes ShipmentIdServer to disk
    private void writeObject(java.io.ObjectOutputStream output) throws IOException {
        try {
            output.defaultWriteObject();
            output.writeObject(shipmentServer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
        try {
            input.defaultReadObject();
            if (shipmentServer == null) {
                shipmentServer = (ShipmentIdServer) input.readObject();
            } else {
                input.readObject();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
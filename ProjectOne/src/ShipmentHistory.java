package ProjectOne.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class ShipmentHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id, date;
    private LinkedList<Shipment> shipmentItems = new LinkedList<Shipment>();
    transient DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDateTime now;

    ShipmentHistory(String id, LinkedList<Shipment> shipmentItems) {
        now = LocalDateTime.now();
        this.id = id;
        this.date = dtf.format(now);
        this.shipmentItems = shipmentItems;
    }

    ShipmentHistory(String id) {
        now = LocalDateTime.now();
        this.id = id;
        this.date = dtf.format(now);
    }

    // gets shipment id
    public String getId() {
        return id;
    }

    // gets shipment date
    public String getDate() {
        return date;
    }

    // gets shipment products
    public Iterator getProducts() {
        return shipmentItems.iterator();
    }

    // setting shipment id
    public void setId(String id) {
        this.id = id;
    }

    // setting shipment date
    public void setDate(String date) {
        this.date = date;
    }

    // set shipment items into shipment
    public void setShipmentItems(LinkedList<Shipment> shipmentItems) {
        this.shipmentItems = shipmentItems;
    }

    public String toString() {
        return "Date: " + date + " ID: " + id + " Shipment Items: " + shipmentItems;
    }

}

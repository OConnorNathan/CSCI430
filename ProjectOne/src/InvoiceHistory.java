package ProjectOne.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;


import java.io.Serializable;

public class InvoiceHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id, date;
    private LinkedList<Invoice> InvoiceItems = new LinkedList<Invoice>();
    transient DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDateTime now;

    InvoiceHistory(String id, LinkedList<Invoice> InvoiceItems) {
        now = LocalDateTime.now();
        this.id = id;
        this.date = dtf.format(now);
        this.InvoiceItems = InvoiceItems;
    }

    InvoiceHistory(String id) {
        now = LocalDateTime.now();
        this.id = id;
        this.date = dtf.format(now);
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Iterator getProducts() {
        return InvoiceItems.iterator();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }
 
    public void setShipmentItems(LinkedList<Invoice> shipmentItems) {
        this.InvoiceItems = shipmentItems;
    }

    public String toString() {
        return "ID: " + id + " Product Name: " + products + " Quantity: " + quantity + " Price: " + price;
    }
}

/*******************************************************************
 * 
 * Project 1: Invoice, implementation
 * File: Invoice.java
 * 
 * Author: Joseph Hoversten
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Book.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/
import java.io.*;
import java.util.*;

public class Invoice implements Serializable{
    
    private String date;
    private int cid;
    private double total;
    private int id;
    private List<Wish> wishlist = new LinkedList<Wish>();

    public Invoice(String date, int cid, List<Wish> wishlist, double total) {
        this.date = date;
        this.cid = cid;
        this.id = InvoiceIdServer.instance().getId();
        this.total = total;
        this.wishlist = wishlist;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getID() {
        return id;
    }

    public String getDate(){
        return date;
    }

    public Iterator<Wish> getWishlist() {
        return wishlist.iterator();
    }

    public void setWishlist(List<Wish> wishlist) {
        this.wishlist = wishlist;
    }

    public String toString(){
        return "date: " + date + " id: " + id + " Client: " + cid + " total: " + total + " Items: " + wishlist.toString() + "\n";
    }
}
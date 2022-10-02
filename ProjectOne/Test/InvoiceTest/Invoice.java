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

import java.util.*;

public class Invoice{
    
    private String date;
    private double total;
    private int id;
    private List<Wish> wishlist = new LinkedList<Wish>();

    public Invoice(String date, List<Wish> wishlist, double total) {
        this.date = date;
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

    public int getId() {
        return id;
    }

    public Iterator<Wish> getWishlist() {
        return wishlist.iterator();
    }

    public void setWishlist(List<Wish> wishlist) {
        this.wishlist = wishlist;
    }

    public String toString(){
        return "date: " + date + "id: " + id + "total: " + total + "Items: " + wishlist.toString();
    }
}
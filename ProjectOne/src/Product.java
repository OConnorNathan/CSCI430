/*******************************************************************
 * 
 * Project 1: Warehouse, Product implementation
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

import java.io.*;
import java.util.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pid;
    private String description;
    private int quantity;
    private double price;
    private double wholesalePrice;
    private Waitlist waitlist = new Waitlist();
  
  
    public Product(String description, int quantity, double price, double wholesalePrice){
        this.pid = ProductIdServer.instance().getId();
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
    }

    public int getPID(){
        return pid;
    }
    public String getDesc(){
        return description;
    }
    public int getQuant(){
        return quantity;
    }
    public double getPrice(){
        return price;
    }
    public double getWSPrice(){
        return wholesalePrice;
    }
    public Iterator<Wait> getWaits()throws CloneNotSupportedException{
        return waitlist.getWaits();
    }

    public boolean addWait(Wait wait){
        return waitlist.insertWait(wait);
    }
    public boolean fulfillWait(int cid){
        return waitlist.fulfillWait(cid);
    }

    public boolean setDesc(String description){
        this.description = description;
        return true;
    }
    public boolean setQuant(int quantity){
        this.quantity = quantity;
        return true;
    }
    public boolean setPrice(double price){
        this.price = price;
        return true;
    }
    public boolean setWSPrice(double wholeSalePrice){
        this.wholesalePrice = wholeSalePrice;
        return true;
    }

    public String toString(){
        return "pid: " + pid + ", description: " + description + 
                ", quantity: " + quantity + ", price: " + price + 
                ", wholesale price: " + wholesalePrice + 
                ", waitlist: " + waitlist.toString() + "\n";
    }
}

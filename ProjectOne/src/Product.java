/*******************************************************************
 * 
 * Project 1: Warehouse, Inventory implementation
 * File: Product.java
 * 
 * Author: Jacob Haapoja
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Book.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/

package ProjectOne.src;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pid;
    private String description;
    private int quantity;
    private float price;
    private float wholesalePrice;
  
  
    public Product(String description, int quantity, float price, float wholesalePrice){
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
    public float getPrice(){
        return price;
    }
    public float getWSPrice(){
        return wholesalePrice;
    }

    public boolean setDesc(String description){
        this.description = description;
        return true;
    }
    public boolean setQuant(int quantity){
        this.quantity = quantity;
        return true;
    }
    public boolean setPrice(float price){
        this.price = price;
        return true;
    }
    public boolean setWSPrice(float wholesalePrice){
        this.wholesalePrice = wholesalePrice;
        return true;
    }

    public String toString(){
        return "pid: " + pid + "description: " + description + "quantity: " + quantity + "price: " + price + "wholesale price: " + wholesalePrice;
    }
}

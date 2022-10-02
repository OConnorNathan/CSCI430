/*******************************************************************
 * 
 * Project 1: Warehouse, Wish implementation
 * File: Wish.java
 * 
 * Author: Nathan O'Connor
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Book.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/

import java.io.*;

public class Wish implements Serializable{
    private static final long serialVersionUID = 1L;
    private int pid;
    private int quantity;
    private double price;

    public Wish(int pid, int quantity, double price){
        this.pid = pid;
        this.quantity = quantity;
        this.price = price;
    }
    public void setPID(int pid){
        this.pid = pid;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public int getPID(){
        return pid;
    }
    public int getQuantity(){
        return quantity;
    }
    public double getPrice(){
        return price;
    }
    public String toString(){
        return "ProductID: " + pid + ", Quantity: " + quantity + ", Price: " + price + " ";
    }
}

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
package ProjectOne.src;


public class Wish {
    private int pid;
    private int quantity;
    private float price;

    public Wish(int pid, int quantity, float price){
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
    public void setPrice(float price){
        this.price = price;
    }
    public int getPID(){
        return pid;
    }
    public int getQuantity(){
        return quantity;
    }
    public float getPrice(){
        return price;
    }
    public String toString(){
        return pid + " " + quantity + " " + price;
    }
}

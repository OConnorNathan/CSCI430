/*******************************************************************
 * 
 * Project 1: Warehouse, Wait implementation
 * File: Wait.java
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

public class Wait implements Serializable, Cloneable{
    private static final long serialVersionUID = 1L;
    private int cid;
    private int quantity;

    public Wait clone() throws CloneNotSupportedException{
        return (Wait) super.clone();
    }
    public Wait(int cid, int quantity){
        this.cid = cid;
        this.quantity = quantity;
    }
    
    public int getCID(){
        return cid;
    }
    public int getQuantity(){
        return quantity;
    }

    public String toString(){
        return "cid: " + cid + ", quantity: " + quantity;
    }
}

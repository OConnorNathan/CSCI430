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
package ProjectOne.src;

import java.io.*;

public class Wait implements Serializable{
    private static final long serialVersionUID = 1L;
    private int cid;
    private int pid;
    private int quantity;

    public Wait(int cid, int pid, int quantity){
        this.cid = cid;
        this.pid = pid;
        this.quantity = quantity;
    }
    
    public int getCID(){
        return cid;
    }
    public int getPID(){
        return pid;
    }
    public int getQuantity(){
        return quantity;
    }

    public String toString(){
        return "cid: " + cid + "pid: " + pid + "quantity: " + quantity;
    }
}

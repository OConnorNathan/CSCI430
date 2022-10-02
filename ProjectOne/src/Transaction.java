/*******************************************************************
 * 
 * Project 1: Warehouse, Transaction implementation
 * File: Transaction.java
 * 
 * Author: Nathan O'Connor
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Book.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/

import java.io.Serializable;
public class Transaction implements Serializable{
    private static final long serialVersionUID = 1L;
    private String date;
    private int type;
    private int invoiceID;
    private double dollarAmount;
    
    public Transaction(String date, int type, int invoiceID, double dollarAmount){
        this.date = date;
        this.type = type;
        this.invoiceID = invoiceID;
        this.dollarAmount = dollarAmount;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setInvoiceID(int invoiceID){
        this.invoiceID = invoiceID;
    }

    public void setDollarAmount(double dollarAmount){
        this.dollarAmount = dollarAmount;
    }

    public String getDate(){
        return date;
    }

    public int getType(){
        return type;
    }

    public int getInvoiceID(){
        return invoiceID;
    }

    public double getDollarAmount(){
        return dollarAmount;
    }

    public String toString(){
        return "Date: " + date + ", Type: " + type + ", InvoiceID: " + invoiceID + ", Cost: " + dollarAmount + " ";
    }
}
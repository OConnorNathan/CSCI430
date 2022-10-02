/*******************************************************************
 * 
 * Project 1: Warehouse, Client implementation
 * File: Client.java
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
import java.util.*;

public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private int cid;
  private String name;
  private String address;
  private WishList wishlist = new WishList();
  private TransactionHistory transactHist = new TransactionHistory();

  public  Client (String name, String address) {
    this.name = name;
    this.address = address;
    this.cid = (ClientIdServer.instance()).getId();
  }

  public String getName() {
    return name;
  }

  public int getCID() {
    return cid;
  }

  public String getAddress() {
    return address;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public void setAddress(String newAddress) {
    this.address = newAddress;
  }
  
  public boolean addWish(int pid, int quantity, double price){
    return wishlist.addWish(new Wish(pid, quantity, price));
  }

  public boolean removeWish(int pid){
    return wishlist.removeWish(pid);
  }

  public Wish findWish(int pid){
    return wishlist.findWish(pid);
  }

  public Iterator<Wish> getWishs(){
    return wishlist.getWishs();
  }
  
  public boolean addTransaction(Transaction transact){
    return transactHist.addTransaction(transact);
  }

  public boolean addTransaction(String date, int type, int invoiceID, double dollarAmount){
    return transactHist.addTransaction(new Transaction(date, type, invoiceID, dollarAmount));
  }

  public boolean removeTransaction(int invoiceID){
    return transactHist.removeTransaction(invoiceID);
  }

  public double makePayment(double payment){
    return transactHist.makePayment(payment);
  }

  public double getBalance(){
    return transactHist.getBalance();
  }

  public Iterator<Transaction> getTransactions(){
    return transactHist.getTransactions();
  }

  public String toString() {
    String string = "ClientID: " + cid + ", Name: " + name + ", Address: " + address + " " + wishlist.toString() + transactHist.toString() + "\n";
    return string;
  }

}

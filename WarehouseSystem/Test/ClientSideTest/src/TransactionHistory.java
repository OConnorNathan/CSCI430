/*******************************************************************
 * 
 * Project 1: Warehouse, TransactionHistory implementation
 * File: TransactionHistory.java
 * 
 * Author: Nathan O'Connor
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Book.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/

import java.util.*;
import java.io.Serializable;
public class TransactionHistory implements Serializable{
    
    private LinkedList<Transaction> transactions;
    private double balance;

    public TransactionHistory(double balance, LinkedList<Transaction> transactions){
        this.transactions = transactions;
        this.balance = balance;
    }
    public TransactionHistory(){
        transactions = new LinkedList<Transaction>();
        balance = 0;
    }

    public boolean addTransaction(Transaction transact){
        balance += transact.getDollarAmount();
        return transactions.add(transact);
    }

    public boolean removeTransaction(int invoiceID){

        for(Transaction t: transactions){
            if(t.getInvoiceID() == invoiceID){
                return transactions.remove(t);
            }
        }
        return false;
    }
    
    public Iterator getTransactions(){
        return transactions.iterator();
    }

    public double makePayment(double payment){
        balance = balance - payment;
        return balance;
    }

    public double getBalance(){
        return balance;
    }

     public String toString(){
        String temp = new String("Transaction History: ");
        if(transactions.isEmpty()){
            temp += "None ";
        }
        for(Transaction t: transactions){
            temp += t.toString();
        }
        return temp + "Balance: " + balance;
     }
}

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
package ProjectOne.src;

import java.util.*;

public class TransactionHistory {
    
    private LinkedList<Transaction> transactions;

    public TransactionHistory(){
        transactions = new LinkedList<Transaction>();
    }

    public boolean addTransaction(Transaction transact){
        return transactions.add(transact);
    }

    public boolean removeTransaction(Transaction transact){

        for(Transaction t: transactions){
            if(t == transact){
                return transactions.remove(t);
            }
        }
        return false;
    }
    
    public Iterator getTransactions(){
        return transactions.iterator();
     }
}

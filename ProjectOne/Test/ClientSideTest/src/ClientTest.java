/*******************************************************************
 * 
 * Project 1: Warehouse, ClientSide Tester
 * File: ClientTest.java
 * 
 * Author: Nathan O'Connor
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 *******************************************************************/

import java.io.*;
import java.util.*;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        Client c1 = new Client("Nathan", "1234 Monticello street");
        c1.addWish(789, 2, 20.01);
        c1.addTransaction("02-02-2022", 1, 890, 20.01);

        Client c2 = new Client("Matthew", "4321 Rogers Avenue");
        c2.addWish(987, 1, 9.99);
        c2.addTransaction("01-01-2001", 0, 523, 9.99);

        ClientList clientList = ClientList.instance();
        if(clientList.insertClient(c1)){
            System.out.println("C1 Inserted into ClientList");
        }

        if(clientList.insertClient(c2)){
            System.out.println("C2 Inserted into ClientList");
        }
        System.out.println("\nAll Client Information\n" + clientList.toString());

        //Make a payment for the client
        for(final Iterator<Client> c = clientList.getClients(); c.hasNext();){
            final Client value = c.next();
            if(value.getCID() == 1){
                value.makePayment(3.00);
            }

            else if(value.getCID() == 2){
                value.makePayment(5.00);
            }
        }
        System.out.println("\nAfter client payment\n" + clientList.toString());

        //Remove a Wish from the Client WishList
        for(final Iterator<Client> c = clientList.getClients(); c.hasNext();){
            final Client value = c.next();
            if(value.getCID() == 1){
                value.removeWish(789);
            }
        }
        System.out.println("\nAfter Wish removed from C1" + clientList.toString());

        //Remove Transaction from Client TransactionList
        for(final Iterator<Client> c = clientList.getClients(); c.hasNext();){
            final Client value = c.next();
            if(value.getCID() == 2){
                value.removeTransaction(523);
            }
        }
        System.out.println("\nAfter Transaction Removed From C2\n" + clientList.toString());

        //Remove Client from clientList
        for(final Iterator<Client> c = clientList.getClients(); c.hasNext();){
            final Client value = c.next();
            if(value.getCID() == 1){
                clientList.removeClient(1);
            }
        }
        System.out.println("\nAfter C1 removed from Clientlist\n" + clientList.toString());
    }
}
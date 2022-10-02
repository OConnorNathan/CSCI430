/*******************************************************************
 * 
 * Project 1: Warehouse, ClientList implementation
 * File: ClientList.java
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
import java.io.*;
public class ClientList implements Serializable {
  private static final long serialVersionUID = 1L;
  private LinkedList<Client> clients = new LinkedList<Client>();
  private static ClientList clientList;
  private ClientList() {
  }

  public static ClientList instance() {
    if (clientList == null) {
      return (clientList = new ClientList());
    } else {
      return clientList;
    }
  }

  public boolean insertClient(Client client) {
    return clients.add(client);
  }

  public boolean removeClient(int cid){
    for(Client c: clients){
      if(c.getCID() == cid){
        return clients.remove(c);
      }
    }
    return false;
  }

  public Client findClient(int cid){
    for(Client c: clients){
      if(c.getCID() == cid){
        return c;
      }
    }
    return null;
  }

  public Iterator<Client> getClients(){
     return clients.iterator();
  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(clientList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (clientList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (clientList == null) {
          clientList = (ClientList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  
  public String toString() {
    String temp = new String();
    for(Client c: clients){
      temp += c.toString();
    }
    return temp;
  }
}

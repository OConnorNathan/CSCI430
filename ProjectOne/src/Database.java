/*******************************************************************
 * 
 * Project 1: Warehouse, Database implementation
 * File: Database.java
 * 
 * Author: Nathan O'Connor, Jake Happoja, Blake Hoosline, Joseph Hoversten
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Library.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/

import java.util.*;
import java.io.*;
public class Database implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final int BOOK_NOT_FOUND  = 1;
  public static final int BOOK_NOT_ISSUED  = 2;
  public static final int BOOK_HAS_HOLD  = 3;
  public static final int BOOK_ISSUED  = 4;
  public static final int HOLD_PLACED  = 5;
  public static final int NO_HOLD_FOUND  = 6;
  public static final int OPERATION_COMPLETED= 7;
  public static final int OPERATION_FAILED= 8;
  public static final int NO_SUCH_MEMBER = 9;
  private Inventory inventory;
  private ClientList clientList;
  private InvoiceHistory invoiceHistory;
  private ShipmentList shipmentList;
  private static Database database;
  private Database() {
    inventory = Inventory.instance();
    clientList = ClientList.instance();
    invoiceHistory = InvoiceHistory.instance();
    shipmentList = ShipmentList.instance();
  }
  public static Database instance() {
    if (database == null) {
      ClientIdServer.instance(); // instantiate all singletons
      return (database = new Database());
    } else {
      return database;
    }
  }
  public Product addProduct(String description, int quantity, float price, float wholesalePrice) {
    Product product = new Product(description, quantity, price, wholesalePrice);
    if (inventory.insertProduct(product)) {
      return (product);
    }
    return null;
  }
  public Client addClient(String name, String address) {
    Client client = new Client(name, address);
    if (clientList.insertClient(client)) {
      return (client);
    }
    return null;
  }

  public Iterator getProducts() {
      return inventory.getProducts();
  }

  public Iterator getClients() {
      return clientList.getClients();
  }

  public boolean addWishListItem(int clientID, int productID, int quantity){

    Product product = inventory.searchInventory(productID);
    if(product == null){
      return false;
    }
    for(Iterator<Client> c = clientList.getClients(); c.hasNext();){
      if(c.next().getCID() == clientID){
        c.next().addWish(productID, quantity, (double) product.getPrice() * quantity);
        return true;
      }
    }
    return false;
  }

  public Wish removeWishListItem(int clientID, int productID){
    Client client = clientList.findClient(clientID);
    Product product = inventory.searchInventory(productID);
    if(client == null || product == null){
      return null;
    }
    else{
      for(final Iterator<Wish> w = client.getWishs(); w.hasNext();){
        final Wish value = w.next();
        if(value.getPID() == productID){
            return value;
        }
      }
    }
    return null;
  }
  public static Database retrieve() {
    try {
      FileInputStream file = new FileInputStream("LibraryData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientIdServer.retrieve(input);
      return database;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return null;
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      return null;
    }
  }
  public static  boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("LibraryData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(database);
      output.writeObject(ClientIdServer.instance());
      return true;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(database);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      input.defaultReadObject();
      if (database == null) {
        database = (Database) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public String toString() {
    return inventory + "\n" + clientList;
  }
}

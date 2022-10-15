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

  /* DATA MEMBERS */

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

  /* CONSTRUCTOR METHODS */

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

  /* POPULATOR METHODS */

  public Product addProduct(String description, int quantity, double price, double wholesalePrice) {
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

  /* QUERY METHODS */

  public String toString() {
    return inventory + "\n" + clientList;
  }

  public Iterator<Product> getProducts() {
    return inventory.getProducts();
  }

  public Product findProduct(int pid){
    return inventory.findProduct(pid);
  }

  public Iterator<Client> getClients() {
    return clientList.getClients();
  }

  public Client findClient(int cid){
    return clientList.findClient(cid);
  }

  public Iterator<Invoice> getInvoices() {
    return invoiceHistory.getInvoices();
  }

  public Invoice findInvoice(int invID){
    return invoiceHistory.findInvoice(invID);
  }

  public Iterator<Shipment> getShipments() {
    return shipmentList.getShipments();
  }

  public Shipment findShipment(int shipID){
    return shipmentList.findShipment(shipID);
  }

  public int checkQuant(int productID){
    return inventory.findProduct(productID).getQuant();
  }

  public Iterator<Wish> getClientWishList(int clientID) throws CloneNotSupportedException{
    for(Iterator<Client> c = clientList.getClients(); c.hasNext();){
      final Client client = c.next();
      if(client.getCID() == clientID){
        return client.getWishs();
      }
    }
    return null;
  }

  public Iterator<Transaction> getClientTransactions(int clientID){
    for(Iterator<Client> c = clientList.getClients(); c.hasNext();){
      final Client client = c.next();
      if(client.getCID() == clientID){
        return client.getTransactions();
      }
    }
    return null;
  }

  public String getOutStandingBalances(){
    String clientBalances = new String();
    for(Iterator<Client> c = clientList.getClients(); c.hasNext();){
      final Client client = c.next();
      if(client.getBalance() > 0){
        clientBalances += client.getCID() + ": $" + client.getBalance() + "\n";
      }
    }
    return clientBalances;
  }
  
  /* PRIMARY BUSINESS PROCESSES */

  public boolean addWishListItem(int clientID, int productID, int quantity){

    Product product = inventory.findProduct(productID);
    if(product == null){
      return false;
    }
    Client c = clientList.findClient(clientID);
    c.addWish(productID, quantity, (quantity * product.getPrice()));
    return true;
  }

  public boolean removeWishListItem(int clientID, int productID, int quantity){
    Client c = clientList.findClient(clientID);
    Wish w = c.findWish(productID);
    if(w != null){
      if(w.getQuantity() - quantity <= 0){
        c.removeWish(productID);
      }
      else{
        w.setQuantity(w.getQuantity() - quantity);
      }
      return true;
    }
    return false;
  }

  public double makePayment(int clientID, double payment){
    Client client = clientList.findClient(clientID);
    client.addTransaction(new Transaction(java.time.LocalDate.now().toString(), 0, 0, payment));
    return client.getBalance();
  }

  public Invoice makeOrder(int clientID, LinkedList<Wish> orderWishList) throws CloneNotSupportedException{
    Client client = clientList.findClient(clientID);
    double total = 0;
  
    if(client != null){
      for(Wish w: orderWishList){
        Product product = findProduct(w.getPID());
        if(product.getQuant() - w.getQuantity() < 0){
          int overQuantity = w.getQuantity() - product.getQuant();
          product.addWait(new Wait(client.getCID(), overQuantity));
          w.setQuantity(product.getQuant());
          product.setQuant(0);

        }
        else{
          product.setQuant(product.getQuant() - w.getQuantity());
        }
        total += w.getPrice();
      }
      if(total != 0){
        Invoice invoice = new Invoice(java.time.LocalDate.now().toString(), client.getCID(), orderWishList, total);
        invoiceHistory.insertInvoice(invoice);
        
        client.addTransaction(invoice.getDate(), 1, invoice.getID(), invoice.getTotal());
        return invoice;
      }
    }
    return null;
  }

  public Iterator<Wait> acceptShipment(int productID, int quantity) throws CloneNotSupportedException{
    Product product = inventory.findProduct(productID);
    if(product == null){
      return null;
    }

    if (shipmentList.insertShipment(new Shipment(productID, quantity, product.getWSPrice() * quantity))) {
      return product.getWaits();
    } 
    return null;
  }

  public Invoice createOrder(int pid, int cid, int quantity){
    Invoice invoice = null;
    List<Wish> orders = new LinkedList<Wish>();
    Product product = inventory.findProduct(pid);
    if(product != null){
      product.fulfillWait(cid);
      Client client = clientList.findClient(cid);
      if(client != null){
        orders.add(new Wish(pid, quantity, quantity * product.getPrice()));
        invoice = new Invoice(java.time.LocalDate.now().toString(), cid, orders, quantity * product.getPrice());
        invoiceHistory.insertInvoice(invoice);
        client.addTransaction(invoice.getDate(), 1, invoice.getID(), invoice.getTotal());
      }
    }
    return invoice;
  }

  public void addWait(int cid, int pid, int quantity){
    Product product = inventory.findProduct(pid);
    if(product != null){
      product.addWait(new Wait(cid, quantity));
    }
  }

  public void updateProductQuantity(int pid, int quantity){
    Product product = inventory.findProduct(pid);
    product.setQuant(quantity + product.getQuant());
  }

  /* DATABASE BACKUP METHODS */
  
  public static Database retrieve() {
    try {
      FileInputStream file = new FileInputStream("DatabaseData");
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

  public static boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("DatabaseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(database);
      output.writeObject(ClientIdServer.instance());
      output.close();
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
}

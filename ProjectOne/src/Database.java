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

  public Iterator<Product> getProducts() {
    return inventory.getProducts();
  }

  public Iterator<Client> getClients() {
    return clientList.getClients();
  }

  public Iterator<Invoice> getInvoices() {
    return invoiceHistory.getInvoices();
  }

  public Iterator<Shipment> getShipments() {
    return shipmentList.getShipments();
  }

  public boolean addWishListItem(int clientID, int productID, int quantity){

    Product product = inventory.searchInventory(productID);
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
        double temp = w.getPrice() / w.getQuantity();
        w.setPrice(temp * (w.getQuantity() - quantity));
        w.setQuantity(w.getQuantity() - quantity);
      }
      return true;
    }
    return false;
  }


  public Iterator<Wish> getClientWishList(int clientID){
    for(Iterator<Client> c = clientList.getClients(); c.hasNext();){
      final Client client = c.next();
      if(client.getCID() == clientID){
        return client.getWishs();
      }
    }
    return null;
  }

  public Shipment addShipment(int productID, int quantity, double price) {
    Shipment shipment = new Shipment(productID, quantity, price);
    if (shipmentList.insertShipment(shipment)) {
      /* UPDATE INVENTORY STOCK */
      /* FULFILL WAITLIST */
      return shipment;
    } return null;
  }


  public double makePayment(int clientID, double payment){
    Client client = clientList.findClient(clientID);
    double balance = client.makePayment(payment);
    client.addTransaction(new Transaction(java.time.LocalDate.now().toString(), 0, 0, payment));
    return balance;
  }

  public void makeOrder(int clientID, List<Integer> productIDs){
    List<Wish> orderWishList = new ArrayList<Wish>();
    Client client = clientList.findClient(clientID);
  
    if(client != null){
      for(Iterator<Wish> w = client.getWishs(); w.hasNext();){

        Wish wish = w.next();

        for(int i = 0; i < productIDs.size(); i++){
          if(wish.getPID() == productIDs.get(i)){

            orderWishList.add(wish);
            client.removeWish(wish.getPID());

          }
        }
      }

      for(Iterator<Product> p = inventory.getProducts(); p.hasNext();){
        Product product = p.next();
        for(int i = 0; i < orderWishList.size(); i++){
          if(orderWishList.get(i).getPID() == product.getPID()){
            if(product.getQuant() - orderWishList.get(i).getQuantity() < 0){

              int overQuantity = orderWishList.get(i).getQuantity() - product.getQuant();
              product.addWait(new Wait(client.getCID(), overQuantity));
              orderWishList.get(i).setQuantity(product.getQuant());
              orderWishList.get(i).setPrice(product.getQuant() * product.getPrice());
              product.setQuant(0);

            }
            else{
              product.setQuant(product.getQuant() - orderWishList.get(i).getQuantity());
            }
          }
        }
      }

      double total = 0;
      for(Wish w: orderWishList){
        total += (w.getPrice());
      }

      Invoice invoice = new Invoice(java.time.LocalDate.now().toString(), orderWishList, total);
      invoiceHistory.insertInvoice(invoice);
      
      client.addTransaction(invoice.getDate(), 1, invoice.getId(), invoice.getTotal());
    }
  }

  public String getOutStandingBalances(){
    String clientBalances = new String();
    for(Iterator<Client> c = clientList.getClients(); c.hasNext();){
      final Client client = c.next();
      if(client.getBalance() > 0){
        clientBalances += client.getCID() + ": " + client.getBalance();
      }
    }
    return clientBalances;
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

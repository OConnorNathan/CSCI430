/*******************************************************************
 * 
 * Project 1: Warehouse, UI implementation
 * File: UserInterface.java
 * 
 * Author: Nathan O'Connor, Jake Happoja, Blake Hoosline, Joseph Hoversten
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: UserInterface.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/

import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {

  /* DATA MEMBERS */

  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Database database;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int VIEW_INVENTORY = 3;
  private static final int ADD_ITEM_WISHLIST = 4;
  private static final int REMOVE_ITEM_WISHLIST = 5;
  private static final int SHOW_CLIENT_WISHLIST = 6;
  private static final int ADD_SHIPMENT = 7;
  private static final int MAKE_PAY = 8;
  private static final int MAKE_ORDER = 9;
  private static final int GET_OUTSTANDING_BALANCES = 10;
  private static final int GET_TRANSACTIONS = 11;
  private static final int SHOW_CLIENTS = 12;
  private static final int SHOW_INVOICES = 13;
  private static final int FIND_INVOICES = 14;
  private static final int SAVE = 15;
  private static final int RETRIEVE = 16;
  private static final int HELP = 17;

  /* CONSTRUCTORS */

  private UserInterface() {
    if (yesOrNo("Look for saved data and  use it?")) {
      retrieve();
    } else {
      database = Database.instance();
    }
  }

  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
  }

  /* MAIN */

  public static void main(String[] s) throws CloneNotSupportedException{
    UserInterface.instance().process();
  }

  /* I/O METHODS */

  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }

  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }
  
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command: " + " for help enter " + HELP ));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 17 as explained below:");
    System.out.println(EXIT + " Exit\n");
    System.out.println(ADD_CLIENT + " Add Client");
    System.out.println(ADD_PRODUCT + " Add Product");
    System.out.println(VIEW_INVENTORY + " Show Inventory");
    System.out.println(ADD_ITEM_WISHLIST + " Add Product to a Wishlist");
    System.out.println(REMOVE_ITEM_WISHLIST + " Remove a Product From a Wishlist");
    System.out.println(SHOW_CLIENT_WISHLIST + " Show Client Wishlist");
    System.out.println(ADD_SHIPMENT + " Add a Shipment");
    System.out.println(MAKE_PAY + " Make a Payment Towards a Balance");
    System.out.println(MAKE_ORDER + " Order From a Wishlist");
    System.out.println(GET_OUTSTANDING_BALANCES + " Find All Outstanding Balances");
    System.out.println(GET_TRANSACTIONS + " Find All Transactions of a Client");
    System.out.println(SHOW_CLIENTS + " Show All Clients");
    System.out.println(SHOW_INVOICES + " Show All Invoices");
    System.out.println(FIND_INVOICES + " Find a Specific Invoice");
    System.out.println(SAVE + " Save Data");
    System.out.println(RETRIEVE + " Retrieve Data");
    System.out.println(HELP + " Help");
  }

  public void process() throws CloneNotSupportedException{
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case EXIT:              break;
        case ADD_CLIENT:        addClient();
                                break;
        case ADD_PRODUCT:       addProduct();
                                break;
        case VIEW_INVENTORY:    showInventory();
                                break;
        case ADD_ITEM_WISHLIST: addItemToWishList();
                                break;
        case REMOVE_ITEM_WISHLIST:  removeItemFromWishList();
                                break;
        case SHOW_CLIENT_WISHLIST: showClientWishList();
                                break;
        case ADD_SHIPMENT:      addShipment();
                                break;
        case MAKE_PAY:          makePayment();
                                break;
        case MAKE_ORDER:        makeOrder();
                                break;
        case GET_OUTSTANDING_BALANCES: getOustandingBalances();
                                break;
        case GET_TRANSACTIONS:  getClientTransactions();
                                break;
        case SHOW_CLIENTS:      showClients();
                                break;
        case SHOW_INVOICES:     showInvoices();
                                break;
        case FIND_INVOICES:     findInvoice();
                                break;
        case SAVE:              save();
                                break;
        case RETRIEVE:	        retrieve();
                                break; 		 		
        case HELP:              help();
                                break;
      }
    }
  }

  /* POPULATOR METHODS */

  public void addClient() {
    String name = getToken("Enter Client name");
    String address = getToken("Enter address");
    Client result;
    result = database.addClient(name, address);
    if (result == null) {
      System.out.println("Could not add member");
    }
    System.out.println(result);
  }
  
  public void addProduct(){
    String description = getToken("Enter a description");
    int quantity = Integer.parseInt(getToken("Enter a quantity"));
    float price = Float.parseFloat(getToken("Enter the individual price"));
    float wholesalePrice = Float.parseFloat(getToken("Enter the whole sale price"));
    Product result;
    result = database.addProduct(description, quantity, price, wholesalePrice);
    if(result == null){
      System.out.println("Could not add a product");
    }
    System.out.println(result);
  }

  /* QUERY METHODS */

  public void showInventory(){
    Iterator<Product> allProducts = database.getProducts();
    while(allProducts.hasNext()){
      Product product = (Product)(allProducts.next());
      System.out.println(product.toString());
    }
  }

  public void showClientWishList() throws CloneNotSupportedException{
    int clientID = Integer.parseInt(getToken("Enter a Client ID"));
    Iterator<Wish> wishlist = database.getClientWishList(clientID);
      while(wishlist.hasNext()){
      Wish wish = (Wish)(wishlist.next());
      System.out.println(wish.toString());
    }
  }

  public void getOustandingBalances(){
  String result = database.getOutStandingBalances();
  if(result == null){
    System.out.println("No outstanding balances");
  }
  System.out.println(result);
 }
 
 public void getClientTransactions(){
      int clientID = Integer.parseInt(getToken("Enter a client ID"));
      Iterator<Transaction> allTransactions = database.getClientTransactions(clientID);
      System.out.println("If the Type is 1 it is an order. If it is 0 it is a payment");
      while(allTransactions.hasNext()){
        Transaction transaction = (Transaction)(allTransactions.next());
        System.out.println(transaction.toString());
      }
  }

  public void showClients() {
    Iterator<Client> allClients = database.getClients();
    while (allClients.hasNext()){
    Client client = (Client)(allClients.next());
        System.out.println(client.toString());
    }
  }

  public void showInvoices(){
    Iterator<Invoice> allInvoices = database.getInvoices();
    while (allInvoices.hasNext()){
      Invoice invoice = (Invoice)(allInvoices.next());
      System.out.println(invoice.toString());
    }
  }

  public void findInvoice(){
    int invoiceID = Integer.parseInt(getToken("Enter an InvoiceID: "));
    Invoice invoice = database.findInvoice(invoiceID);
    if(invoice != null){
      System.out.println(invoice);
    }
    else{
      System.out.println("Invoice does not exist");
    }
  }

  /* PRIMARY BUSINESS PROCESSES */

  public void addItemToWishList(){
    int clientID = Integer.parseInt(getToken("Enter a Client ID"));
    int productID = Integer.parseInt(getToken("Enter a product ID"));
    int quantity = Integer.parseInt(getToken("Enter a quantity"));
    boolean result;
    result = database.addWishListItem(clientID, productID, quantity);
    if(result == false){
      System.out.println("Failed to add wish");
    }
    else{
      System.out.println("Wish added successfully");
    }
  }

  public void removeItemFromWishList(){
    int clientID = Integer.parseInt(getToken("Enter a Client ID"));
    int productID = Integer.parseInt(getToken("Enter a product ID"));
    int quantity = Integer.parseInt(getToken("Enter a quantity"));
    boolean result;
    result = database.removeWishListItem(clientID, productID, quantity);
    if(result == false){
      System.out.println("Failed to remove wish");
    }
    else{
      System.out.println("Wish removed successfully");
    }
  }
 
  public void addShipment(){
    int productID = Integer.parseInt(getToken("Enter Product ID"));
    int quantity = Integer.parseInt(getToken("Enter a quantity"));
    double price = Double.parseDouble(getToken("Enter the price"));
    Shipment result;
    result = database.acceptShipment(productID, quantity, price);
    if(result == null){
      System.out.println("Couldn't add the shipment");
    }
    else{
      System.out.println(result);
    }
  }

  public void makePayment(){
    int clientID = Integer.parseInt(getToken("Enter a client ID"));
    double payment = Double.parseDouble(getToken("Enter a payment"));
    double balance;
    balance = database.makePayment(clientID, payment);
    System.out.println("New Client Balance: " + balance);
  }
 
  public void makeOrder() throws CloneNotSupportedException{
    int clientID = Integer.parseInt(getToken("Enter a client ID"));
    Iterator<Wish> wishs = database.getClientWishList(clientID);
    LinkedList<Wish> orderWishList = new LinkedList<Wish>();
    while(wishs.hasNext()){
      Wish wishy = wishs.next();
      System.out.println(wishy);
      System.out.println("Product in Stock Quantity: " + database.checkQuant(wishy.getPID()));

      if(yesOrNo("Do you want to add the wish to the order?")){
        database.removeWishListItem(clientID, wishy.getPID(), wishy.getQuantity());
      
        if(!yesOrNo("Do you want to add the whole wish to the order?")){
          int newQuant = Integer.parseInt(getToken("Enter a new quantity: "));
          wishy.setQuantity(newQuant);
        }
        orderWishList.add(wishy);
      }
    }
    Invoice invoice = database.makeOrder(clientID, orderWishList);
    System.out.println(invoice);
  }

  /* DATABASE BACKUP METHODS */

  private void save() {
    if (database.save()) {
      System.out.println(" The database has been successfully saved in the file DatabaseData. \n" );
    } else {
      System.out.println(" There has been an error in saving the database. \n" );
    }
  }

  private void retrieve() {
    try {
      Database tempDatabase = Database.retrieve();
      if (tempDatabase != null) {
        System.out.println("The database has been successfully retrieved from the file DatabaseData. \n" );
        database = tempDatabase;
      } else {
        System.out.println("File does not exist; creating new database" );
        database = Database.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
}
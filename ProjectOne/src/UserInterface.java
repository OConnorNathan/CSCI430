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
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Database database;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int VIEW_INVENTORY = 3;
  private static final int ADD_ITEM_WISHLIST = 4;
  private static final int REMOVE_ITEM_WISHLIST = 5;
  private static final int SHOW_CLIENT_WISHLIST = 6
  private static final int ADD_SHIPMENT = 7;
  private static final int MAKE_PAY = 8;
  private static final int MAKE_ORDER = 9;
  private static final int GET_OUTSTANDING_BALANCES = 10;
  private static final int GET_TRANSACTIONS = 11;
  private static final int SHOW_CLIENTS = 12;
  private static final int SHOW_INVOICES = 13;
  private static final int SAVE = 14;
  private static final int RETRIEVE = 15;
  private static final int HELP = 16;
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
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }
  public Calendar getDate(String prompt) {
    do {
      try {
        Calendar date = new GregorianCalendar();
        String item = getToken(prompt);
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        date.setTime(df.parse(item));
        return date;
      } catch (Exception fe) {
        System.out.println("Please input a date as mm/dd/yy");
      }
    } while (true);
  }
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 15 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_CLIENT + " to add a client");
    System.out.println(ADD_PRODUCT + " to  add product");
    System.out.println(VIEW_INVENTORY + " to view products in inventory");
    System.out.println(ADD_ITEM_WISHLIST + " to add item to wishlist");
    System.out.println(REMOVE_ITEM_WISHLIST + " to remove an item from wihslist");
    System.out.println(ADD_SHIPMENT + " add shipment invoice for shipping");
    System.out.println(MAKE_PAY + " to make payment of the balance");
    System.out.println(MAKE_ORDER + " will make an order in transaction and updates the clients balance");
    System.out.println(GET_OUTSTANDING_BALANCES + "shows the outstanding balance of invoioce");
    System.out.println(GET_TRANSACTIONS + " to  print transactions");
    System.out.println(SHOW_CLIENTS + " print a list of the clients");
    System.out.println(SHOW_INVOICES + " prints the invoices");
    System.out.println(SAVE + " to  save data");
    System.out.println(RETRIEVE + " to  retrieve");
    System.out.println(HELP + " for help");
  }

  public void addClient() {
    String name = getToken("Enter member name");
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

  public void showInventory(){
    Iterator allProducts = database.getProducts();
    while(allProducts.hasNext()){
      Product product = (Product)(allProducts.next());
      System.out.println(product.toString());
    }
  }

  public void addItemToWishList(){
    int clientID = Integer.parseInt(getToken("Enter a Client ID"));
    int productID = Integer.parseInt(getToken("Enter a product ID"));
    int quantity = Integer.parseInt(getToken("Enter a quantity"));
    boolean result;
    result = database.addWishListItem(clientID, productID, quantity);
    if(result == false){
      System.out.println("Failed to add wish");
    }
    System.out.println("Wish added successfully");
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
    System.out.println("Wish removed successfully");
  }

  public void showClientWishList(){
    int clientID = Integer.parseInt(getToken("Enter a Client ID"));
    Iterator wishlist = database.getClientWishList(clientID);
    while(wishlist.hasNext()){
      Wish wish = (Wish)(wishlist.next());
      System.out.println(wish.toString());
    }
  }
 
 public void addShipment(){
    int productID = Integer.parseInt(getToken("Enter Product ID"));
    int quantity = Integer.parseInt(getToken("Enter a quantity"));
    double price = Double.parseDouble(getToken("Enter the price"));
    Shipment result;
    result = database.addShipment(productID, quantity, price);
    if(result == null){
      System.out.println("Couldn't add the shipment");
    }
    System.out.println(result);
 }

 public void makePayment(){
  int clientID = Integer.parseInt(getToken("Enter a client ID"));
  double payment = Double.parseDouble(getToken("Enter a payment"));
  double balance;
  balance = database.makePayment(clientID, payment);
  if(balance == null){
    System.out.println("Failed to make a payment");
  }
  System.out.println(balance);
 }
 
 public void makeOrder(){
  int clientID = Integer.parseInt(getToken("Enter a client ID"));

 }

 public void getOustandingBalances(){
  String result = database.getOutStandingBalances()
  if(result == null){
    System.out.println("No outstanding balances");
  }
  System.out.println(result);
 }

  public void getClientTransactions(){
      int clientID = Integer.parseInt(getToken("Enter a client ID"));
      String result = database.getClientTransactions(clientID);
      if(result == null){
        System.out.println("Failed to make a payment");
      }
      System.out.println(result);
  }

  public void showClients() {
    Iterator allClients = database.getClients();
    while (allClients.hasNext()){
  Client client = (Client)(allClients.next());
        System.out.println(client.toString());
    }
  }

  public void showInvoices(){
    Iterator allInvoices = database.getInvoices();
    while (allInvoices.hasNext()){
      Invoice invoice = (Invoice)(allInvoices.next());
            System.out.println(invoice.toString());
      }
  }

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


  
  public void process() {
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
        case SHOW_INVOICES:     showInvoices();
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
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
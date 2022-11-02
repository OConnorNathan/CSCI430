package UserInterface;

import java.util.*;
import java.text.*;
import java.io.*;
import BackEnd.*;

public class ManagerState extends WhState{

    /* Data Members */

  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Database database;
  private WhContext context;
  private static ManagerState instance;
  private static final int EXIT = 0;
  private static final int FIND_SHIPMENT = 1;
  private static final int SHOW_SHIPMENTS = 2;
  private static final int ADD_PRODUCT = 3;
  private static final int ADD_SHIPMENT = 4;
  private static final int CLERK_VIEW = 5;
  private static final int HELP = 6;

  private ManagerState() {
      super();
      database = Database.instance();
      //context = LibContext.instance();
  }

  public static ManagerState instance() {
    if (instance == null) {
      instance = new ManagerState();
    }
    return instance;
  }

  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(UIHelper.getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void addProduct(){
    String description = UIHelper.getToken("Enter a description");
    int quantity = Integer.parseInt(UIHelper.getToken("Enter a quantity"));
    double price = Double.parseDouble(UIHelper.getToken("Enter the individual price"));
    double wholesalePrice = Double.parseDouble(UIHelper.getToken("Enter the whole sale price"));
    Product result;
    result = database.addProduct(description, quantity, price, wholesalePrice);
    if(result == null){
      System.out.println("Could not add a product");
    }
    System.out.println(result);
  }

  public void addShipment() {
    int productID = Integer.parseInt(UIHelper.getToken("Enter Product ID"));
    int shipmentQuantity = Integer.parseInt(UIHelper.getToken("Enter a quantity"));
    Iterator<Wait> result;
    result = database.acceptShipment(productID, shipmentQuantity);
    if(result == null){
      System.out.println("This product has no waitlisted items");
    }
    else{
      while(result.hasNext() && shipmentQuantity > 0){
        Wait waity = result.next();
        int orderQuantity = waity.getQuantity();
        System.out.println(waity);
        System.out.println("Shipment Quantity: " + shipmentQuantity);

        if(!UIHelper.yesOrNo("Do you want to skip this wait?")){
          if(!UIHelper.yesOrNo("Do you want to order the existing quantity")){
            orderQuantity = Integer.parseInt(UIHelper.getToken("New Quantity: "));
          }
          if(orderQuantity > shipmentQuantity){
            System.out.println(database.createOrder(productID, waity.getCID(), shipmentQuantity));
            database.addWait(waity.getCID(), productID, orderQuantity - shipmentQuantity);
            shipmentQuantity = 0;
          }
          else{
            System.out.println(database.createOrder(productID, waity.getCID(), orderQuantity));
            shipmentQuantity -= orderQuantity;
          }

        }
      }
      database.updateProductQuantity(productID, shipmentQuantity);
    }
  }
  public void showShipments(){
    Iterator<Shipment> allShipments = database.getShipments();
    while(allShipments.hasNext()){
      System.out.println(allShipments.next().toString());
    }
  }

  public void findShipment(){
    int shipmentID = Integer.parseInt(UIHelper.getToken("Enter an ShipmentID: "));
    Shipment shipment = database.findShipment(shipmentID);
    if(shipment != null){
      System.out.println(shipment);
    }
    else{
      System.out.println("Shipment does not exist");
    }
  }

  public void clerkMenu()
  {  
      (WhContext.instance()).changeState(1);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 12 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(FIND_SHIPMENT + " Find a specific shipment");
    System.out.println(SHOW_SHIPMENTS + " Show all shipments");
    System.out.println(ADD_PRODUCT + " To add a product");
    System.out.println(ADD_SHIPMENT + " To add a shipment");
    System.out.println(CLERK_VIEW + " To become a clerk");
    System.out.println(HELP + " for help");
  }

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {

        case FIND_SHIPMENT:
                            findShipment();
                            break;
        case SHOW_SHIPMENTS:
                            showShipments();
                            break;
        case ADD_PRODUCT:
                            addProduct();
                            break;
        case ADD_SHIPMENT:
                            addShipment();
                            break;
        case CLERK_VIEW:
                            clerkMenu();
                            break;
        case HELP:          help();
                            break;
      }
    }
    logout();
  }

  public void run(){
        process();
  }

  public void logout(){
    (WhContext.instance()).changeState(3); // exit code 2, indicates error
  }
}

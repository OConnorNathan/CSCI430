package UserInterface;

import java.util.*;
import BackEnd.*;

public class ClerkState extends WhState {
  private static Database database;
  private WhContext context;
  private static ClerkState instance;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int MAKE_PAY = 2;
  private static final int SHOW_CLIENTS = 3;
  private static final int VIEW_INVENTORY = 4;
  private static final int GET_OUTSTANDING_BALANCES = 5;
  private static final int CLIENT_VIEW = 6;
  private static final int SHOW_WAITLIST = 7;
  private static final int HELP = 8;

  private ClerkState() {
      super();
      database = Database.instance();
      //context = LibContext.instance();
  }

  public static ClerkState instance() {
    if (instance == null) {
      instance = new ClerkState();
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

  public void addClient() {
    String name = UIHelper.getToken("Enter Client name");
    String address = UIHelper.getToken("Enter address");
    Client result;
    result = database.addClient(name, address);
    if (result == null) {
      System.out.println("Could not add member");
    }
    System.out.println(result);
  }

  public void showInventory(){
    Iterator<Product> allProducts = database.getProducts();
    while(allProducts.hasNext()){
      Product product = (Product)(allProducts.next());
      System.out.println(product.toString());
    }
  }

  public void showClients() {
    Iterator<Client> allClients = database.getClients();
    while (allClients.hasNext()){
    Client client = (Client)(allClients.next());
        System.out.println(client.toString());
    }
  }

  public void getOustandingBalances(){
    String result = database.getOutStandingBalances();
    if(result == null){
      System.out.println("No outstanding balances");
    }
    System.out.println(result);
   }

   public void makePayment(){
    int clientID = Integer.parseInt(UIHelper.getToken("Enter a client ID"));
    double payment = Double.parseDouble(UIHelper.getToken("Enter a payment"));
    double balance;
    balance = database.makePayment(clientID, payment);
    System.out.println("New Client Balance: " + balance);
  }
   
  public void clientView(){
    int userID = Integer.parseInt(UIHelper.getToken("Please input the client id: "));
    if (Database.instance().findClient(userID) != null){
      (WhContext.instance()).setClient(userID);      
      (WhContext.instance()).changeState(0);
    }
    else 
      System.out.println("Invalid user id."); 
  }

  public void showProductWaitlist(){
    System.out.println(database.findProduct(Integer.parseInt(UIHelper.getToken("Enter a pid: "))).toString());
  }

  public void help() {
    System.out.println("Enter a number between 0 and 8 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_CLIENT + " to add a client");
    System.out.println(MAKE_PAY + " to make payment");
    System.out.println(SHOW_CLIENTS + " to list all clients ");
    System.out.println(VIEW_INVENTORY + " to view the entire inventory");
    System.out.println(GET_OUTSTANDING_BALANCES + " to display outstanding balances");
    System.out.println(CLIENT_VIEW + " to switch to the user menu");
    System.out.println(SHOW_WAITLIST + " to show the waitlist for a product");
    System.out.println(HELP + " for help");
  }

  public void logout()
  {
    if ((WhContext.instance()).getLogin() == WhContext.IsManager){
      (WhContext.instance()).changeState(2);
    }
    else{
      (WhContext.instance()).changeState(3);
    }
  }
 

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:        addClient();
                                break;
        case MAKE_PAY:          makePayment();
                                break;
        case SHOW_CLIENTS:      showClients();
                                break;
        case VIEW_INVENTORY:    showInventory();
                                break;
        case GET_OUTSTANDING_BALANCES:      
                                getOustandingBalances();
                                break;
        case CLIENT_VIEW:       clientView();
                                break;
        case SHOW_WAITLIST:     showProductWaitlist();
                                break;
        case HELP:              help();
                                break;
      }
    }
    logout();
  }
  public void run() {
    process();
  }
}

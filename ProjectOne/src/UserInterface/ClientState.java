package UserInterface;

import java.util.*;
import BackEnd.*;

public class ClientState extends WhState{
 private static ClientState clientstate;
  private static Database database;
  private static final int EXIT = 0;
  private static final int SHOW_CLIENT_DETAILS = 1;
  private static final int GET_TRANSACTIONS = 2;
  private static final int SHOW_CLIENT_WAITLIST = 3;
  private static final int VIEW_INVENTORY = 4;
  private static final int SHOW_CLIENT_WISHLIST = 5;
  private static final int ADD_ITEM_WISHLIST = 6;
  private static final int REMOVE_ITEM_WISHLIST = 7;
  private static final int MAKE_ORDER = 8;
  private static final int HELP = 9;
  
  private ClientState() {
    database = Database.instance();
  }

  public static ClientState instance() {
    if (clientstate == null) {
      return clientstate = new ClientState();
    } else {
      return clientstate;
    }
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

  //TO DO: Modify to only show this particular client
  public void showClients() {
    Iterator<Client> allClients = database.getClients();
    while (allClients.hasNext()){
    Client client = (Client)(allClients.next());
        System.out.println(client.toString());
    }
  }
  
  public void getClientTransactions(){
    int clientID = Integer.parseInt(UIHelper.getToken("Enter a client ID"));
    Iterator<Transaction> allTransactions = database.getClientTransactions(clientID);
    System.out.println("If the Type is 1 it is an order. If it is 0 it is a payment");
    while(allTransactions.hasNext()){
      Transaction transaction = (Transaction)(allTransactions.next());
      System.out.println(transaction.toString());
    }
}

  public void showClientWaitlist(){

}

  public void showInventory(){
    Iterator<Product> allProducts = database.getProducts();
    while(allProducts.hasNext()){
      Product product = (Product)(allProducts.next());
      System.out.println(product.toString());
    }
  }

    public void showClientWishList(){
        int clientID = Integer.parseInt(UIHelper.getToken("Enter a Client ID"));
        Iterator<Wish> wishlist = database.getClientWishList(clientID);
        while(wishlist.hasNext()){
        Wish wish = (Wish)(wishlist.next());
        System.out.println(wish.toString());
        }
    }

  public void addItemToWishList(){
    int clientID = Integer.parseInt(UIHelper.getToken("Enter a Client ID"));
    int productID = Integer.parseInt(UIHelper.getToken("Enter a product ID"));
    int quantity = Integer.parseInt(UIHelper.getToken("Enter a quantity"));
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
    int clientID = Integer.parseInt(UIHelper.getToken("Enter a Client ID"));
    int productID = Integer.parseInt(UIHelper.getToken("Enter a product ID"));
    int quantity = Integer.parseInt(UIHelper.getToken("Enter a quantity"));
    boolean result;
    result = database.removeWishListItem(clientID, productID, quantity);
    if(result == false){
      System.out.println("Failed to remove wish");
    }
    else{
      System.out.println("Wish removed successfully");
    }
  }
 
  public void makeOrder(){
    int clientID = Integer.parseInt(UIHelper.getToken("Enter a client ID"));
    Iterator<Wish> wishs = database.getClientWishList(clientID);
    LinkedList<Wish> orderWishList = new LinkedList<Wish>();
    while(wishs.hasNext()){
      Wish wishy = wishs.next();
      System.out.println(wishy);
      System.out.println("Product in Stock Quantity: " + database.checkQuant(wishy.getPID()));

      if(UIHelper.yesOrNo("Do you want to add the wish to the order?")){
        database.removeWishListItem(clientID, wishy.getPID(), wishy.getQuantity());
      
        if(!UIHelper.yesOrNo("Do you want to add the whole wish to the order?")){
          int newQuant = Integer.parseInt(UIHelper.getToken("Enter a new quantity: "));
          wishy.setQuantity(newQuant);
        }
        orderWishList.add(wishy);
      }
    }
    Invoice invoice = database.makeOrder(clientID, orderWishList);
    if(invoice != null){
      System.out.println(invoice);
    }
  }

  public void help() {
    System.out.println("Enter a number between 0 and 12 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(SHOW_CLIENT_DETAILS + " To show client details");
    System.out.println(GET_TRANSACTIONS + " To show client transactions");
    System.out.println(SHOW_CLIENT_WAITLIST + " To show client's waitlist");
    System.out.println(VIEW_INVENTORY + "To show inventory");
    System.out.println(SHOW_CLIENT_WISHLIST + " To show this client's wishlist");
    System.out.println(ADD_ITEM_WISHLIST + " To add an item to a wishlist");
    System.out.println(REMOVE_ITEM_WISHLIST + " To remove an item from a wishlist");
    System.out.println(MAKE_ORDER + " To make an order");
    System.out.println(HELP + " for help");
  }

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {

        case SHOW_CLIENT_DETAILS:    
                                showClients();
                                break;
        case GET_TRANSACTIONS:       
                                getClientTransactions();
                                break;
        case SHOW_CLIENT_WAITLIST:        
                                showClientWaitlist();
                                break;
        case VIEW_INVENTORY:       
                                showInventory();
                                break;
        case SHOW_CLIENT_WISHLIST:
                                showClientWishList();
                                break;
        case ADD_ITEM_WISHLIST:
                                addItemToWishList();
                                break;
        case REMOVE_ITEM_WISHLIST:
                                removeItemFromWishList();
                                break;
        case MAKE_ORDER:
                                makeOrder();
                                break;
        case HELP:              help();
                                break;
      }
    }
    logout();
  }

  public void run(){
        process();
  }

  public void logout()
  {
    if ((WhContext.instance()).getLogin() == WhContext.IsClerk)
       { //stem.out.println(" going to clerk \n ");
         (WhContext.instance()).changeState(1); // exit with a code 1
        }
    else if (WhContext.instance().getLogin() == WhContext.IsClient)
       {  //stem.out.println(" going to login \n");
        (WhContext.instance()).changeState(0); // exit with a code 2
       }
    else 
       (WhContext.instance()).changeState(2); // exit code 2, indicates error
  }
 
}


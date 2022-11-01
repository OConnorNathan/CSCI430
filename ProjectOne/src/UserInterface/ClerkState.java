package UserInterface;

import java.util.*;
import java.text.*;
import java.io.*;
import BackEnd.*;

public class ClerkState extends WhState {
  private static Database database;
  private WhContext context;
  private static ClerkState instance;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int MAKE_PAY = 6;
  private static final int SHOW_CLIENTS = 8;
  private static final int VIEW_INVENTORY = 9;
  private static final int GET_OUTSTANDING_BALANCES = 12;
  private static final int HELP = 19;

  private static final int CLIENT_VIEW = 20;
  private static final int SHOW_WAITLIST = 21;

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

  public void help() {
    System.out.println("Enter a number between 0 and 12 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_CLIENT + " to add a client");
    System.out.println(MAKE_PAY + " to  make payment");
    System.out.println(SHOW_CLIENTS + " to list all clients ");
    System.out.println(VIEW_INVENTORY + " to view the entire inventory");
    System.out.println(GET_OUTSTANDING_BALANCES + "displays the outstanding balance ");
    System.out.println(USERMENU + " to  switch to the user menu");
    System.out.println(HELP + " for help");
  }

  public void usermenu()
  {
    String userID = UIHelper.getToken("Please input the user id: ");
    if (Database.instance().searchMembership(userID) != null){
      (WhContext.instance()).setUser(userID);      
      (WhContext.instance()).changeState(1);
    }
    else 
      System.out.println("Invalid user id."); 
  }

  public void logout()
  {
    (WhContext.instance()).changeState(0); // exit with a code 0
  }
 

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_MEMBER:        addMember();
                                break;
        case ADD_BOOKS:         addBooks();
                                break;
        case RETURN_BOOKS:      returnBooks();
                                break;
        case REMOVE_BOOKS:      removeBooks();
                                break;
        case PROCESS_HOLD:      processHolds();
                                break;
        case USERMENU:          usermenu();
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

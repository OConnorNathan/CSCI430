package UserInterface;

import java.io.*;
import BackEnd.*;

public class LoginState extends WhState{
  private static final int CLIENT_LOGIN = 0;
  private static final int CLERK_LOGIN = 1;
  private static final int MANAGER_LOGIN = 2;
  private static final int EXIT = 3;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
  private WhContext context;
  private static LoginState instance;
  private LoginState() {
      super();
     // context = LibContext.instance();
  }

  public static LoginState instance() {
    if (instance == null) {
      instance = new LoginState();
    }
    return instance;
  }

  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(UIHelper.getToken("Enter command:" ));
        if (value <= EXIT && value >= CLIENT_LOGIN) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  private void clerk(){
    (WhContext.instance()).setLogin(WhContext.IsClerk);
    (WhContext.instance()).changeState(1);
  }

  private void client(){
    int clientID = Integer.parseInt(UIHelper.getToken("Please input the client id: "));
    if (Database.instance().findClient(clientID) != null){
      (WhContext.instance()).setLogin(WhContext.IsClient);
      (WhContext.instance()).setClient(clientID);      
      (WhContext.instance()).changeState(0);
    }
    else 
      System.out.println("Invalid client id.");
  } 
  
  private void manager(){
    (WhContext.instance()).setLogin(WhContext.IsManager);
    (WhContext.instance()).changeState(2);
  }

  public void process() {
    int command;
    System.out.println("input 0 to login as Client\n"+ 
                        "input 1 to login as Clerk\n" +
                        "input 2 to login as Manager\n" + 
                        "input 3 to exit the system\n");     
    while ((command = getCommand()) != EXIT) {

      switch (command) {
        case CLIENT_LOGIN:      client();
                                break;
        case CLERK_LOGIN:       clerk();
                                break;
        case MANAGER_LOGIN:     manager();
                                break;
        default:                System.out.println("Invalid choice");                      
      }
      System.out.println("input 0 to login as Client\n"+ 
                          "input 1 to login as Clerk\n" + 
                          "input 2 to login as Manager\n" + 
                          "input 3 to exit the system\n");  
    }
    
    (WhContext.instance()).changeState(3);
  }

  public void run() {
    process();
  }
}

package UserInterface;

import java.util.*;
import java.io.*;
import BackEnd.*;
public class WhContext {
  
  private int currentState;
  private static Database database;
  private static WhContext context;
  private int currentUser;
  private int userID;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  public static final int IsClient = 0;
  public static final int IsClerk = 1;
  public static final int IsManager = 2;
  private WhState[] states;
  private int[][] nextState;

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

  private void retrieve() {
    try {
      Database tempDatabase = database.retrieve();
      if (tempDatabase != null) {
        System.out.println(" The database has been successfully retrieved from the file DatabaseData \n" );
        database = tempDatabase;
      } else {
        System.out.println("File doesnt exist; creating new database" );
        database = Database.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }

  public void setLogin(int code)
  {currentUser = code;}

  public void setClient(int uID)
  { userID = uID;}

  public int getLogin()
  { return currentUser;}

  public int getUser()
  { return userID;}

  private WhContext() { //constructor
    System.out.println("In WhContext constructor");
    if (yesOrNo("Look for saved data and  use it?")) {
      retrieve();
    } else {
        database = Database.instance();
    }
    // set up the FSM and transition table;
    states = new WhState[4];
    states[0] = ClientState.instance();
    states[1] = ClerkState.instance(); 
    states[2] = ManagerState.instance();
    states[3] = LoginState.instance();
    nextState = new int[4][4];
    nextState[0][0] = 0;nextState[0][1] = 1;nextState[0][2] = -2;nextState[0][3] = 3;
    nextState[1][0] = 0;nextState[1][1] = 1;nextState[1][2] = 2;nextState[1][3] = 3;
    nextState[2][0] = -2;nextState[2][1] = 1;nextState[2][2] = 2;nextState[2][3] = 3;
    nextState[3][0] = 0;nextState[3][1] = 1;nextState[3][2] = 2;nextState[3][3] = -1;
    currentState = 3;
  }

  public void changeState(int transition)
  {
    //System.out.println("current state " + currentState + " \n \n ");
    currentState = nextState[currentState][transition];
    if (currentState == -2) 
      {System.out.println("Error has occurred"); terminate();}
    if (currentState == -1) 
      terminate();
    //System.out.println("current state " + currentState + " \n \n ");
    states[currentState].run();
  }

  private void terminate()
  {
   if (yesOrNo("Save data?")) {
      if (database.save()) {
         System.out.println(" The warehouse has been successfully saved in the file DatabaseData \n" );
       } else {
         System.out.println(" There has been an error in saving \n" );
       }
     }
   System.out.println(" Goodbye \n "); System.exit(0);
  }

  public static WhContext instance() {
    if (context == null) {
       System.out.println("calling constructor");
      context = new WhContext();
    }
    return context;
  }

  public void process(){
    states[currentState].run();
  }
  
  public static void main (String[] args){
    WhContext.instance().process(); 
  }
}

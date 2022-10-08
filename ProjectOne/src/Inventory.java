/*******************************************************************
 * 
 * Project 1: Warehouse, Inventory implementation
 * File: Inventory.java
 * 
 * Author: Jacob Haapoja
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Catalog.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/

import java.util.*;
import java.io.*;

public class Inventory implements Serializable {
  private static final long serialVersionUID = 1L;
  private LinkedList<Product> products = new LinkedList<Product>();
  private static Inventory inventory;
  private Inventory(){}

  public static Inventory instance(){
      if (inventory == null){
          return (inventory = new Inventory());
      } else {
          return inventory;
      }
  }

  public boolean insertProduct(Product product){
      products.add(product);
      return true;
  }

  public Product findProduct(int pid){
    for (int i = 0; i < products.size(); i++){
      if (products.get(i).getPID() == pid){
        return products.get(i);
      }
    }
    return null;
  }

  public Product findProduct(String description){
    for(int i = 0; i < products.size(); i++){
      if (products.get(i).getDesc() == description){
        return products.get(i);
      }
    }
    return null;
  }

  public Iterator<Product> getProducts(){
      return products.iterator();
  }

  private void writeObject(java.io.ObjectOutputStream output){
      try{
          output.defaultWriteObject();
          output.writeObject(inventory);
      } catch (IOException ioe){
          System.out.println(ioe);
      }
  }

  private void readObject(java.io.ObjectInputStream input){
      try {
        if (inventory != null) {
          return;
        } else {
          input.defaultReadObject();
          if (inventory == null) {
            inventory = (Inventory) input.readObject();
          } else {
            input.readObject();
          }
        }
      } catch(IOException ioe) {
        System.out.println("in Inventory readObject \n" + ioe);
      } catch(ClassNotFoundException cnfe) {
        cnfe.printStackTrace();
      }
  }

  public String toString(){
      return products.toString();
  }
}

/*******************************************************************
 * 
 * Project 1: Warehouse, WaitList implementation
 * File: Waitlist.java
 * 
 * Author: Jacob Haapoja
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Catalog.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/
package ProjectOne.src;

import java.util.*;
import java.io.*;

public class Waitlist implements Serializable{
    private static final long serialVersionUID = 1L;
    private LinkedList<Wait> waits= new LinkedList<Wait>();
    private static Waitlist waitlist;
    private Waitlist(){}

    public static Waitlist instance(){
        if (waitlist == null){
            return (waitlist = new Waitlist());
        } else {
            return waitlist;
        }
    }

    public boolean insertWait(Wait wait){
        waits.add(wait);
        return true;
    }

    public boolean fulfillWait(Wait wait){
      int i = waits.indexOf(wait);
      if (i != -1){
        waits.remove(i);
        return true;
      }
      else{
        return false;
      }
    }

    public Iterator getWaits(){
        return waits.iterator();
    }

    private void writeObject(java.io.ObjectOutputStream output){
        try {
            output.defaultWriteObject();
            output.writeObject(waitlist);
          } catch(IOException ioe) {
            System.out.println(ioe);
          }
    }

    private void readObject(java.io.ObjectInputStream input) {
        try {
          if (waitlist != null) {
            return;
          } else {
            input.defaultReadObject();
            if (waitlist == null) {
              waitlist = (Waitlist) input.readObject();
            } else {
              input.readObject();
            }
          }
        } catch(IOException ioe) {
          System.out.println("in Waitlist readObject \n" + ioe);
        } catch(ClassNotFoundException cnfe) {
          cnfe.printStackTrace();
        }
      }
      
      public String toString(){
        return waits.toString();
      }
}


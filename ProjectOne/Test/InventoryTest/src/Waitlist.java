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

import java.util.*;
import java.io.*;

public class Waitlist implements Serializable{
    private static final long serialVersionUID = 1L;
    private LinkedList<Wait> waits= new LinkedList<Wait>();

    public Waitlist(){}

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

    public Iterator<Wait> getWaits(){
        return waits.iterator();
    }

    public String toString(){
      return waits.toString();
    }
}


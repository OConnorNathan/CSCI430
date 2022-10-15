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

    public boolean fulfillWait(int cid){
      for(Wait w: waits){
        if(w.getCID() == cid){
          waits.remove(w);
          return true;
        }
      }
      return false;
    }

    public Iterator<Wait> getWaits() throws CloneNotSupportedException{
      LinkedList<Wait> temp = new LinkedList<Wait>();
      for (Wait W: waits){
          temp.add(W.clone());
      }
      return temp.iterator();
    }

    public String toString(){
      return waits.toString();
    }
}


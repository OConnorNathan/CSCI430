/*******************************************************************
 * 
 * Project 1: Warehouse, WishList implementation
 * File: WishList.java
 * 
 * Author: Nathan O'Connor
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Book.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/

import java.io.*;
import java.util.*;

public class WishList implements Serializable{
    private static final long serialVersionUID = 1L;
    private LinkedList<Wish> wishs;

    public WishList(LinkedList<Wish> wishs){
        this.wishs = wishs;
    }
    public WishList(){
        wishs = new LinkedList<Wish>();
    }
    
    public Wish findWish(int pid){
        for(Wish w: wishs){
            if(w.getPID() == pid){
                return w;
            }
        }
        return null;
    }
    public boolean addWish(Wish wishy){
        return wishs.add(wishy);
    }

    public boolean removeWish(int pid){
        for(Wish w : wishs){
            if(w.getPID() == pid){
                return wishs.remove(w);
            }
        }
        return false;
    }

    public Iterator<Wish> getWishs() throws CloneNotSupportedException{
        LinkedList<Wish> temp = new LinkedList<Wish>();
        for (Wish W: wishs){
            temp.add(W.clone());
        }
        return temp.iterator();
    }

    public String toString(){
        String temp = new String("WishList: ");
        if(wishs.isEmpty()){
            temp += "None ";
        }
        for(Wish w : wishs){
            temp += w.toString();
        }
        return temp;
    }
}

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

    public boolean removeWish(int pid, int quantity){

        for(Iterator<Wish> w = this.getWishs(); w.hasNext();){
            if(w.next().getPID() == pid){
                if(w.next().getQuantity() - quantity <= 0){
                    w.remove();
                }
                else{
                    w.next().setQuantity(w.next().getQuantity() - quantity);
                }
                return true;
            }
        }
        return false;
    }
    
    public Iterator getWishs(){
        return wishs.iterator();
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

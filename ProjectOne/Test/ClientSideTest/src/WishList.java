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

    public boolean addWish(Wish wishy){
        return wishs.add(wishy);
    }

    public boolean removeWish(int pid){

        for(Wish w: wishs){
            if(w.getPID() == pid){
                return wishs.remove(w);
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

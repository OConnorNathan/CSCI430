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
package ProjectOne.src;

import java.io.*;
import java.util.*;

public class WishList implements Serializable{
    
    private LinkedList<Wish> wishs;

    public WishList(){
        wishs = new LinkedList<Wish>();
    }

    public boolean addWish(Wish wishy){
        return wishs.add(wishy);
    }

    public boolean removeWish(Wish wishy){

        for(Wish w: wishs){
            if(w == wishy){
                return wishs.remove(w);
            }
        }
        return false;
    }
    
    public Iterator getWishs(){
        return wishs.iterator();
    }
}

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

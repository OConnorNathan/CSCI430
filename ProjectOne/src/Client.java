
package ProjectOne.src;

import java.io.*;
import java.util.*;
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private int cid;
  private String name;
  private String address;
  private WishList wishlist = new WishList();
  private TransactionHistory transactHist = new TransactionHistory();

  public  Client (String name, String address) {
    this.name = name;
    this.address = address;
    this.cid = ClientIdServer.instance().getId();
  }
  public String getName() {
    return name;
  }
  public int getCID() {
    return cid;
  }
  public String getAddress() {
    return address;
  }

  public void setName(String newName) {
    this.name = newName;
  }
  public void setAddress(String newAddress) {
    this.address = newAddress;
  }
  public void setCID(int cid) {
    this.cid = cid;
  }

  public boolean addWish(Wish w){
    return wishlist.addWish(w);
  }
  public boolean addWish(int pid, int quantity, float price){
    return wishlist.addWish(new Wish(pid, quantity, price));
  }
  public boolean removeWish(Wish w){
    return wishlist.removeWish(w);
  }
  public Iterator getWishs(){
    return wishlist.getWishs();
  }
  
  public boolean addTransaction(Transaction transact){
    return transactHist.addTransaction(transact);
  }
  public boolean addTransaction(String date, int type, int invoiceID, float dollarAmount){
    return transactHist.addTransaction(new Transaction(date, type, invoiceID, dollarAmount));
  }
  public boolean removeTransaction(Transaction transact){
    return transactHist.removeTransaction(transact);
  }
  public Iterator getTransactions(){
    return transactHist.getTransactions();
  }

  public String toString() {
    String string = "Client name " + name + " address " + address + " cid " + cid;
    return string;
  }

}
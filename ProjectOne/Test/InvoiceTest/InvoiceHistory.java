/*******************************************************************
 * 
 * Project 1: InvoiceHistory, implementation
 * File: InvoiceHistory.java
 * 
 * Author: Joseph Hoversten
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 * Based On: Catalog.java by Dr. Ramnath Sarnath
 * 
 *******************************************************************/

import java.util.*;
import java.io.*;

public class InvoiceHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    private LinkedList<Invoice> invoices = new LinkedList<Invoice>();
    private static InvoiceHistory invoiceList;

    private InvoiceHistory(){
    }

    public static InvoiceHistory instance() {
        if (invoiceList == null) {
          return (invoiceList = new InvoiceHistory());
        } else {
          return invoiceList;
        }
    }

    public boolean insertInvoice(Invoice invoice) {
        invoices.add(invoice);
        return true;
    }
    
    public Iterator<Invoice> getInvoices(){
         return invoices.iterator();
    }
      
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
          output.defaultWriteObject();
          output.writeObject(invoiceList);
        } catch(IOException ioe) {
          ioe.printStackTrace();
        }
    }
    private void readObject(java.io.ObjectInputStream input) {
        try {
          if (invoiceList != null) {
            return;
          } else {
            input.defaultReadObject();
            if (invoiceList == null) {
              invoiceList = (InvoiceHistory) input.readObject();
            } else {
              input.readObject();
            }
          }
        } catch(IOException ioe) {
          ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
          cnfe.printStackTrace();
        }
    }
    public String toString() {
        return invoices.toString();
    }
}
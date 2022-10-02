/*******************************************************************
 * 
 * Project 1: Warehouse, Inventory Tester
 * File: InventoryTest.java
 * 
 * Author: Joseph Hoversten
 * Group Number: 2
 * Instructor: Dr. Ramnath Sarnath
 * Class: CSCI 430
 * 
 *******************************************************************/

package ProjectOne.src;

import java.io.*;
import java.util.*;

public class InvoiceTest {
    public static void main(String[] args) throws Exception {
      
        Invoice s1 = new Invoice("9/12/20", 125, 145, "Phone");

        Invoice s2 = new Invoice("9/15/20", 110, 111, "Tablet");

    
        InvoiceList InvoiceList = InvoiceList.instance();
        if (InvoiceList.insertInvoice(s1)) {
            System.out.println("S1 Inserted into InvoiceList");
        }

        if (InvoiceList.insertInvoice(s2)) {
            System.out.println("S2 Inserted into InvoiceList");
        }
        System.out.println("\nInvoice Information\n" + InvoiceList.toString());

    }
}

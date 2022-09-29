import java.util.*;
package Invoice_source;

public class InventoryList {

    private LinkedList<Invoice> invoice;
    
    public InventoryList(){
        invoice = new LinkedList<Invoice>();
    }

    public boolean addInvoice(Invoice invoice) {
        return invoice.add(invoice);
    }

    public boolean removeInvoice(Invoice invoice) {
        return invoice.remove(invoice);
    }

    public boolean searchInvoice(Invoice invoice) {
        return invoice.search(invoice);
    }

    public Iterator getInvoices() {
        return invoice.iterator();
    }
}

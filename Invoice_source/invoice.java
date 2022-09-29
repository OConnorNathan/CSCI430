
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Invoice{

    double total;
    String id;
    private List<Wish> wishlist = new LinkedList<Wish>();
    //private static final long serialVersionUID = 1L;

    public Invoice(
            String date,
            List<Wish> wishlist,
            double total) {
        this.id = "INV" + InvoiceIdServer.instance().getId();
        this.total = total;
        this.wishlist = wishlist;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Wish> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Wish> wishlist) {
        this.wishlist = wishlist;
    }
}

public class Transaction{
    
    private String date;
    private int type;
    private int invoiceID;
    private float dollarAmount;

    public Transaction(){
        this.date = "";
        this.type = -1;
        this.invoiceID = -1;
        this.dollarAmount = 0;

    }
    
    public Transaction(String date, int type, int invoiceID, float dollarAmount){
        this.date = date;
        this.type = type;
        this.invoiceID = invoiceID;
        this.dollarAmount = dollarAmount;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setInvoiceID(int invoiceID){
        this.invoiceID = invoiceID;
    }

    public void setDollarAmount(float dollarAmount){
        this.dollarAmount = dollarAmount;
    }

    public String getDate(){
        return date;
    }

    public int getType(){
        return type;
    }

    public int getInvoiceID(){
        return invoiceID;
    }

    public float getDollarAmount(){
        return dollarAmount;
    }

    public String toString(){
        return date + " " + type + " " + invoiceID + " " + dollarAmount;
    }
}
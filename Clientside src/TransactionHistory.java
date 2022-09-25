import java.util.*;

public class TransactionHistory {
    
    private LinkedList<Transaction> transactions;

    public TransactionHistory(){
        transactions = new LinkedList<Transaction>();
    }

    public boolean addTransaction(Transaction transact){
        return transactions.add(transact);
    }

    public boolean removeTransaction(Transaction transact){

        for(Transaction t: transactions){
            if(t == transact){
                return transactions.remove(t);
            }
        }
        return false;
    }
    
    public Iterator getTransactions(){
        return transactions.iterator();
     }
}

package bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by therina on 2016/05/15.
 */
public class Bank extends Object {
    private String bankName;
    private Client client;
    private BankAccount bankAccount;

    //AccountList
    protected Map<String, BankAccount> bankAccounts;

    //ClientList
    protected Map<String, Client> clients;




    //superclass constructor, instance of a bank with lots of clients
    public Bank(String bankName)
    {
        this.bankName = bankName;
        clients = new HashMap<String, Client>();
        bankAccounts = new HashMap<String, BankAccount>();

    }

    public String getBankName() {
        return bankName;
    }

    public Client getClient() {
        return client;
    }
}

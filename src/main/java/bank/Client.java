package bank;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by therina on 2016/05/15.
 */
public class Client implements Serializable {
    protected String clientName;
    protected long phoneNumber;
    protected String email;
    protected List<BankAccount> bankAccounts;

    public Client(String clientName, long phoneNumber, String email){
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.email= email;
        bankAccounts = new LinkedList<BankAccount>();

    }


    public String getClientName() {
        return clientName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public  String toString()
    {
        return getClientName();
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}









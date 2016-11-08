package main;

import bank.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by therina on 2016/05/24.
 */
public class ProgramState implements Serializable {
    private List<Client> clientList;
    private List<BankAccount> bankAccounts;
    private Client selectedClient;
    private BankAccount selectedBankAccount;


    public  ProgramState()
    {

        clientList = new LinkedList<Client>();
        bankAccounts = new LinkedList<BankAccount>();

    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }



    public List<Client> getClientList() {
        return clientList;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public void setSelectedBankAccount(BankAccount selectedBankAccount){
        this.selectedBankAccount = selectedBankAccount;
    }

    public BankAccount getSelectedbankAccount() {
        return selectedBankAccount;
    }
}

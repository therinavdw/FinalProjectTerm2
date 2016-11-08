package gui;

import bank.BankAccount;
import bank.Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by therina on 2016/06/06.
 */
public class AccountDetailsPanel extends JPanel {
    private BankAccount bankAccount;
    private JLabel client;
    private JLabel balance;
    private JLabel accountNumber;
    private JLabel transactionCount;


    public  AccountDetailsPanel (){
        prepareGUI();
    }

    public void prepareGUI(){
        this.setLayout(new GridLayout(5,1));
        client = new JLabel();
        balance = new JLabel();
        accountNumber = new JLabel();
        transactionCount = new JLabel();


        this.add(client);
        this.add(balance);
        this.add(accountNumber);
        this.add(transactionCount);

    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        refresh();
    }

    public void refresh(){
        client.setText("Name Client:  " + bankAccount.getClient().getClientName());
        balance.setText("Balance: "+ bankAccount.getBalance());
        accountNumber.setText("Account Number:  "+ bankAccount.getAccountNum());
        transactionCount.setText(bankAccount.toString());

    }

}

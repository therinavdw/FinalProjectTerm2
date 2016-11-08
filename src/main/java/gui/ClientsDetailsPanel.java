package gui;

import bank.BankAccount;
import bank.Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by therina on 2016/11/08.
 */
public class ClientsDetailsPanel extends JPanel {

    private Client client;
    private BankAccount bankAccount;
    private JLabel nameLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel bankAccountLabel;


    public ClientsDetailsPanel(){
        prepareGUI();
    }

    public void prepareGUI(){
        this.setLayout(new GridLayout(5,1));
        nameLabel = new JLabel();
        phoneNumberLabel = new JLabel();
        emailLabel = new JLabel();
        bankAccountLabel = new JLabel();

        this.add(nameLabel);
        this.add(phoneNumberLabel);
        this.add(emailLabel);
        this.add(bankAccountLabel);


    }
    public void setClient(Client client){
        this.client = client;
        this.bankAccount = bankAccount;
        refresh();
    }

    public void refresh(){
        nameLabel.setText("Name:  " + client.getClientName().toString());
        phoneNumberLabel.setText("Phone number: "+ client.getPhoneNumber());
        emailLabel.setText("Email:  "+ client.getEmail());
        String accountsMessage  = "";
        for (BankAccount account : client.getBankAccounts()){
            accountsMessage += "Bank Account: "  + account.getAccountNum() + ";" ;
        }
        bankAccountLabel.setText(accountsMessage);

    }

}

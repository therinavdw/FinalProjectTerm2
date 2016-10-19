package gui;

import bank.Bank;
import bank.BankAccount;
import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/06/04.
 */
public class ViewAccountDetailsFrame extends JFrame {
    private ProgramState programState;
    private JPanel fieldsPanel;
    private JPanel buttonPanel;
    private AccountDetailsPanel accountDetailsPanel;
     private JPanel messagePanel;
    private JLabel accNumbInputLabel;
    private JTextField accNumbInputField;
    private JButton searchButton;
    private JButton backButton;

    public ViewAccountDetailsFrame(ProgramState programState) {
        super();
        this.setSize(new Dimension(600, 450));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.programState = programState;
        prepareGUI();
    }
    public void prepareGUI(){
        this.setTitle("Search for Account Details");
        this.setLayout(new GridLayout(4, 1));

        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(1,1));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));

        messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(1,1));

        accountDetailsPanel  = new AccountDetailsPanel();

        accNumbInputLabel = new JLabel("Enter account number");
        accNumbInputField = new JTextField();
        fieldsPanel.add(accNumbInputLabel);
        fieldsPanel.add(accNumbInputField);

        //clientsDetailsPanel = new ClientsDetailsPanel(programState.getClient());
        searchButton = new JButton("Search");
           searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                for (BankAccount account : programState.getBankAccounts()){
                    if(accNumbInputField.getText().equals(account.getAccountNum())){
                        accountDetailsPanel.setBankAccount(account);
                    }

                }
            }
         /*for (BankAccount account : programState.getBankAccounts())
            {
                if(accNumbInputField.getText().equals(String.valueOf(account.getAccountNum()))){
                    accountDetailsPanel.setBankAccount(account);
                }
            }
            */

        });


        //loop through each account in list
        //if the input( account number). equals to value of account,getAccountNumber
        //run the method inside that account and
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new MenuFrame(programState);
                ViewAccountDetailsFrame.this.dispose();
            }
        });
        buttonPanel.add(backButton);
        buttonPanel.add(searchButton);

        messagePanel.add(accountDetailsPanel);



        this.add(fieldsPanel,BorderLayout.NORTH);
        this.add(buttonPanel,BorderLayout.NORTH);
        this.add(messagePanel,BorderLayout.SOUTH);

        this.setVisible(true);


    }
}

package gui;

import bank.Bank;
import bank.BankAccount;
import bank.Client;
import exception.NegativeAmountException;
import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/06/06.
 */
public class WithdrawDepositFrame extends JFrame {
    private ProgramState programState;
    private JPanel fieldsPanel;
    private JPanel buttonsPanel;

    private JLabel clientNameLabel;
    private JComboBox clientNameCombo;
    private JLabel accountNumberLabel;
    private JTextField accountTextField;
    private JTextField amountTextField;
    private JLabel amountLabel;


    private JButton withdrawButton;
    private JButton depositButton;
    private JButton backButton;

    public WithdrawDepositFrame(ProgramState programState) {
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 450));
        this.programState = programState;
        prepareGUI();
    }

    private void prepareGUI() {
        this.setTitle("Deposit or Withdraw money");
        this.setLayout(new GridLayout(2, 1));

        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(4,2));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,3));

        clientNameLabel = new JLabel("Client Name: ");
        accountNumberLabel = new JLabel("Account Number: ");
        amountLabel = new JLabel("Amount: ");



        clientNameCombo = new JComboBox();



        clientNameCombo = new JComboBox();
        for (Client client :programState.getClientList()){
            clientNameCombo.addItem(client);
        }

        amountTextField = new JTextField();
        accountTextField = new JTextField();
        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //loop through each account in list
                for (BankAccount account : programState.getBankAccounts()){
                    //if the input( account number). equals to value of account,getAccountNumber
                    if(accountTextField.getText().equals(account.getAccountNum())){
                        try {
                            account.withdraw(Double.parseDouble(amountTextField.getText()));
                        } catch (NegativeAmountException e) {
                            JOptionPane.showMessageDialog(WithdrawDepositFrame.this, "Please enter a positive amount");
                            e.printStackTrace();
                        }
                    }

                }

                //if the input( account number). equals to value of account,getAccountNumber
                //run the method inside that account and
                new MenuFrame(programState);
                WithdrawDepositFrame.this.dispose();
            }
        });

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                for (BankAccount account : programState.getBankAccounts()){
                    //if the input( account number). equals to value of account,getAccountNumber
                    if(accountTextField.getText().equals(String.valueOf(account.getAccountNum()))){
                        try {
                            account.deposit(Double.parseDouble(amountTextField.getText()));
                        } catch (NegativeAmountException e) {
                            JOptionPane.showMessageDialog(WithdrawDepositFrame.this, "Please enter a positive amount");
                            e.printStackTrace();
                        }
                    }

                }
                new MenuFrame(programState);
                WithdrawDepositFrame.this.dispose();
            }
        });

        backButton =  new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new MenuFrame(programState);
                WithdrawDepositFrame.this.dispose();
            }
        });

        fieldsPanel.add(clientNameLabel);
        fieldsPanel.add(clientNameCombo);
        fieldsPanel.add(accountNumberLabel);
        fieldsPanel.add(accountTextField);

        fieldsPanel.add(amountLabel);
        fieldsPanel.add(amountTextField);

        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(depositButton);
        buttonsPanel.add(backButton);

        this.add(fieldsPanel,BorderLayout.NORTH);
        this.add(buttonsPanel,BorderLayout.CENTER);

        this.setVisible(true);

    }
}

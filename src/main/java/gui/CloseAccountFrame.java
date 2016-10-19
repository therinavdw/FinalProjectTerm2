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
 * Created by therina on 2016/06/09.
 */
public class CloseAccountFrame extends JFrame {
    private ProgramState programState;
    private BankAccount bankAccount;
    private JPanel fieldsPanel;
    private JPanel buttonsPanel;
    private JPanel messagePanel;

    private JLabel closingAccountLabel;
    private JLabel transferringToAccountLabel;
    private JTextField closingAccountField;
    private JTextField transferringToAccField;
    private JButton closeAccountButton;
    private JButton backButton;


    public CloseAccountFrame(ProgramState programState) {
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 450));
        this.programState = programState;
        prepareGUI();
    }

    public void prepareGUI() {
        this.setTitle("Close an account");
        this.setLayout(new GridLayout(4, 1));
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2,1));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,1));

        closingAccountLabel = new JLabel("Closing account:");
        closingAccountField = new JTextField();

        transferringToAccountLabel = new JLabel("Transferring amount in closing account to:");
        transferringToAccField = new JTextField();

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame(programState);
                CloseAccountFrame.this.dispose();
            }
        });

        closeAccountButton = new JButton("Close Account");
        closeAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (BankAccount closingAccount : programState.getBankAccounts()){

                    if(closingAccountField.getText().equals(closingAccount.getAccountNum())){
                       for (BankAccount transferringAccount : programState.getBankAccounts()){
                           if (transferringToAccField.getText().equals(transferringAccount.getAccountNum()))
                           {
                               try {
                                   closingAccount.closeAccount(transferringAccount);
                                   programState.getBankAccounts().remove(closingAccount);
                               } catch (NegativeAmountException e1) {
                                   e1.printStackTrace();
                               }
                           }
                       }
                    }
                }
                new MenuFrame(programState);
                CloseAccountFrame.this.dispose();

            }
        });

        messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(2,1));


        fieldsPanel.add(closingAccountLabel);
        fieldsPanel.add(closingAccountField);
        fieldsPanel.add(transferringToAccountLabel);
        fieldsPanel.add(transferringToAccField);

        buttonsPanel.add(backButton);
        buttonsPanel.add(closeAccountButton);

        this.add(fieldsPanel,BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
        this.add(messagePanel,BorderLayout.SOUTH);

        this.setVisible(true);

    }
}
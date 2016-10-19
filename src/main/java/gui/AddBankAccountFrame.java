package gui;

import bank.*;
import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;

/**
 * Created by therina on 2016/06/04.
 */
public class AddBankAccountFrame extends JFrame {
    private ProgramState programState;
    private JPanel fieldsPanel;
    private JPanel buttonPanel;
    private JPanel messagePanel;

    private JComboBox clientNameComboBox;
    private JLabel clientNameLabel;
    private JLabel accTypeLabel;
    private JComboBox accTypeComboBox;

    private JButton createButton;
    private JTextField amountInputField;
    private JLabel amountLabel;
    private JButton backButton;

    private AccountDetailsPanel accountDetailsPanel;

    public AddBankAccountFrame(ProgramState programState) {
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 450);
        this.programState = programState;
        prepareGUI();

    }
    private void prepareGUI(){
        this.setTitle("Create New Account");
        this.setLayout(new GridLayout(4, 1));
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(3, 1));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));

        clientNameLabel = new JLabel("Name: ");
        accTypeLabel = new JLabel("Account Type: ");

        amountLabel = new JLabel("Amount: ");
        amountInputField = new JTextField();

        messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(1, 1));

        accTypeComboBox = new JComboBox();
        accTypeComboBox.addItem("Diamond Cheque");
        accTypeComboBox.addItem("Gold Cheque");
        accTypeComboBox.addItem("Green Savings");
        accTypeComboBox.addItem("Invested Savings");

        clientNameComboBox = new JComboBox();
        for (Client client :programState.getClientList()){
            clientNameComboBox.addItem(client);
        }

        accountDetailsPanel = new AccountDetailsPanel();

        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                BankAccount bankAccount = null;
                String accounts  = (String) accTypeComboBox.getSelectedItem();
                Client selectedClient = (Client) clientNameComboBox.getSelectedItem();
                String amount =  amountInputField.getText();

                boolean isValid = true;

                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(AddBankAccountFrame.this, "Please enter an amount");
                    isValid = false;
                }




                    else if (accounts.equals("Diamond Cheque")) {
                        bankAccount = new DiamondCheque(selectedClient,new Integer(amountInputField.getText()));
                    } else if (accounts.equals("Gold Cheque")) {
                        bankAccount = new GoldCheque(selectedClient,new Integer(amountInputField.getText()));
                    } else if (accounts.equals("Green Savings")) {
                        bankAccount = new GreenSavings(selectedClient,new Integer(amountInputField.getText()));
                    } else {
                        if (accounts.equals("Invested Savings")) {
                            bankAccount = new InvestedSavings(selectedClient,new Integer(amountInputField.getText()));
                        }
                    }


                programState.getBankAccounts().add(bankAccount);
                selectedClient.getBankAccounts().add(bankAccount);
                if (isValid) {
                    accountDetailsPanel.setBankAccount(bankAccount);
                    JOptionPane.showMessageDialog(AddBankAccountFrame.this, "Success! You added a new bank account. The account number is :" +  bankAccount.getAccountNum(),"Added bank account message",
                            JOptionPane.INFORMATION_MESSAGE);
                    new MenuFrame(programState);
                    AddBankAccountFrame.this.dispose();
                }

            }
        });
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new MenuFrame(programState);
                AddBankAccountFrame.this.dispose();

            }
        });
        messagePanel.add(accountDetailsPanel);

        fieldsPanel.add(clientNameLabel);
        fieldsPanel.add(clientNameComboBox);
        fieldsPanel.add(accTypeLabel);
        fieldsPanel.add(accTypeComboBox);
        fieldsPanel.add(amountLabel);
        fieldsPanel.add(amountInputField);


        buttonPanel.add(createButton);
        buttonPanel.add(backButton);


        this.add(fieldsPanel,BorderLayout.NORTH);
        this.add(buttonPanel,BorderLayout.CENTER );
        this.add(accountDetailsPanel,BorderLayout.SOUTH);
        this.setVisible(true);


    }
}

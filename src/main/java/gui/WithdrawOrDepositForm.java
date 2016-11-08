package gui;

import bank.BankAccount;
import bank.Client;
import exception.NegativeAmountException;
import main.ProgramState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/11/08.
 */
public class WithdrawOrDepositForm extends JPanel {
    private ProgramState programState;
    private Client client;
    private JLabel accountNumberLabel;
    private JLabel accountNameLabel;
    private JLabel transferToLabel;
    private JButton deposit;
    private JButton withdraw;
    private JLabel amountLabel;
    private JTextField amountField;
    private Boolean isValid;

    private BankAccount closingAccount;
    private BankAccount transferringAccount;

    public WithdrawOrDepositForm(ProgramState programState){
        super();
        this.programState = programState;
        Dimension dim = getPreferredSize();
        //set size to 50%
        dim.width = 300;
        this.setPreferredSize(dim);




        Border innerBorder = BorderFactory.createTitledBorder("Transactions");
        Border outerBorder = BorderFactory.createEmptyBorder(10,10,10,10);

        this.setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        prepareGUI();
    }

    private void prepareGUI(){
        accountNameLabel = new JLabel("Account:" );
        accountNumberLabel = new JLabel("" + programState.getSelectedbankAccount());
        transferToLabel = new JLabel("Transfer to account: ");

        amountLabel = new JLabel("Amount");
        amountField = new JTextField();
        deposit = new JButton("Deposit");
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;

                if (amountField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(WithdrawOrDepositForm.this, "Please enter an amount");


                } else  {
                    try {
                        programState.getSelectedbankAccount().deposit(Double.parseDouble(amountField.getText()));
                        JOptionPane.showMessageDialog(WithdrawOrDepositForm.this, "You have deposited:" + amountField.getText());
                    } catch (NegativeAmountException er) {
                        JOptionPane.showMessageDialog(WithdrawOrDepositForm.this, "Please enter a positive amount");
                        er.printStackTrace();
                    }
                }




            }

        });
        withdraw = new JButton("Withdraw");
        withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isValid= true;

                if (amountField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(WithdrawOrDepositForm.this, "Please enter an amount");

                    isValid = false;
                }

                if (isValid.equals(true)) {
                    try {
                        programState.getSelectedbankAccount().withdraw(Double.parseDouble(amountField.getText()));
                        JOptionPane.showMessageDialog(WithdrawOrDepositForm.this, "You have withdrawn:" + amountField.getText());
                    } catch (NegativeAmountException er) {
                        JOptionPane.showMessageDialog(WithdrawOrDepositForm.this, "Please enter a positive amount");
                        er.printStackTrace();
                    }
                }



            }
        });

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //space of cell


        ////////////////////first row/////////////////
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.gridy = 0;
        //take up all space or not
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;

        add(accountNameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
//

        ////////////////////second row/////////////////
        gc.weightx = 1;
        gc.weighty = 0.2;
        //add new row
        gc.gridy = 1;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_END;
        add(accountNumberLabel, gc);

        gc.gridy = 1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;


        ////////////////////Third row/////////////////
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridy = 2;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(amountLabel, gc);

        gc.gridy = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
//

        ////////////////////Fourth row/////////////////
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridy = 3;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(amountField,gc);


        ////////////////////5/////////////////
        gc.weightx = 0;
        gc.weighty = 2;

        gc.gridy = 4;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(withdraw, gc);

        gc.gridy = 4;
        gc.gridx = 1;
        add(deposit,gc);

        ////////////////////6/////////////////



    }


    public void setClient(Client client){
        this.client = client;

    }

    public Client getClient() {
        return client;
    }
}


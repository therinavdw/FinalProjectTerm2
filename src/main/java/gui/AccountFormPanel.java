package gui;

import bank.*;
import main.ProgramState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/10/14.
 */
public class AccountFormPanel extends JPanel {
    private ProgramState programState;
    private Client client;
    private JLabel accountTypeLabel;
    private JLabel amountLabel;
    private JButton createButton;
    private JTextField amountField;
    private JComboBox accountComboBox;



    public AccountFormPanel(ProgramState programState){
        super();
        this.programState = programState;
        Dimension dim = getPreferredSize();
        //set size to 50%
        dim.width = 250;
        this.setPreferredSize(dim);




        Border innerBorder = BorderFactory.createTitledBorder("Add Account");
        Border outerBorder = BorderFactory.createEmptyBorder(10,10,10,10);

        this.setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        prepareGUI();

    }

    private void prepareGUI(){
        accountTypeLabel = new JLabel("Account Type: ");
        amountLabel = new JLabel("Amount: ");
        amountField = new JTextField(20);

        accountComboBox = new JComboBox();
        accountComboBox.setPreferredSize(new Dimension(200,200));
        accountComboBox.addItem("Diamond Cheque");
        accountComboBox.addItem("Gold Cheque");
        accountComboBox.addItem("Green Savings");
        accountComboBox.addItem("Invested Savings");

        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BankAccount bankAccount = null;
                String accounts  = (String) accountComboBox.getSelectedItem();
                Client selectedClient = programState.getSelectedClient();
                String amount =  amountField.getText();

                boolean isValid = true;

                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(AccountFormPanel.this, "Please enter an amount");
                    isValid = false;
                }


                else if (accounts.equals("Diamond Cheque")) {
                    bankAccount = new DiamondCheque(selectedClient,new Integer(amountField.getText()));
                } else if (accounts.equals("Gold Cheque")) {
                    bankAccount = new GoldCheque(selectedClient,new Integer(amountField.getText()));
                } else if (accounts.equals("Green Savings")) {
                    bankAccount = new GreenSavings(selectedClient,new Integer(amountField.getText()));
                } else {
                    if (accounts.equals("Invested Savings")) {
                        bankAccount = new InvestedSavings(selectedClient,new Integer(amountField.getText()));
                    }
                }


                programState.getBankAccounts().add(bankAccount);
                selectedClient.getBankAccounts().add(bankAccount);
                if (isValid) {
//                    accountDetailsPanel.setBankAccount(bankAccount);
                    JOptionPane.showMessageDialog(AccountFormPanel.this, "Success! You added a new bank account. The account number is :" +  bankAccount.getAccountNum(),"Added bank account message",
                            JOptionPane.INFORMATION_MESSAGE);
                    new ClientFrame(programState);
//                    AddBankAccountFrame.this.dispose();
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

        add(accountTypeLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
//

        ////////////////////second row/////////////////
        gc.weightx = 1;
        gc.weighty = 0;
        //add new row
        gc.gridy = 1;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_END;
        add(accountComboBox, gc);

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
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridy = 4;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(createButton, gc);

    }


    public void setClient(Client client){
        this.client = client;

    }

    public Client getClient() {
        return client;
    }
}

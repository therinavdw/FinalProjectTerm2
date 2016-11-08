package gui;

import bank.BankAccount;
import bank.Client;
import main.ProgramState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/10/19.
 */
public class DetailsPanel extends JPanel {
    private JComboBox accountsCombo;
    private  JTextField accountDetails;
    private ProgramState programState;
    private JButton showButton;
    private JPanel fieldPanel;
    private JLabel client;
    private JLabel balance;
    private JLabel accountNumber;
    private JLabel transactionCount;

    private AccountsFrame accountsFrame;

    public DetailsPanel(ProgramState programState){
        super();
        this.programState = programState;
        Dimension dim = getPreferredSize();
        //set size to 50%
        dim.width = 250;
        this.setPreferredSize(dim);




        prepareGUI();

    }

    private void prepareGUI(){
        client = new JLabel();
        balance = new JLabel();
        accountNumber = new JLabel();
        transactionCount = new JLabel();


        accountsCombo = new JComboBox();
        for (BankAccount bankAccount :programState.getBankAccounts()){
            accountsCombo.addItem(bankAccount);
        }
//    accountsCombo.setPreferredSize(new Dimension(0,100));
        accountDetails = new JTextField(20);
        showButton = new JButton("Open");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankAccount bankAccount = (BankAccount) accountsCombo.getSelectedItem();
                programState.setSelectedBankAccount(bankAccount);
                accountsFrame = new AccountsFrame(programState);
                accountsFrame.setBankAccount(bankAccount);
            }
        });
        setLayout(new BorderLayout());

        fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.add(accountsCombo,BorderLayout.WEST);
        fieldPanel.add(showButton,BorderLayout.EAST);

        add(fieldPanel,BorderLayout.NORTH);
        add(accountDetails,BorderLayout.CENTER);

    }

}

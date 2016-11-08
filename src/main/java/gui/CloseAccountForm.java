package gui;

import bank.*;
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
public class CloseAccountForm extends JPanel {
    private ProgramState programState;
    private Client client;
    private JLabel transferFromLabel;
    private JLabel transferFromDetails;
    private JLabel transferToLabel;
    private JButton createButton;
    private JLabel empty;
    private JTextField amountField;
    private JComboBox accountComboBox;
    private BankAccount closingAccount;
    private BankAccount transferringAccount;



    public CloseAccountForm(ProgramState programState){
        super();
        this.programState = programState;
        Dimension dim = getPreferredSize();
        //set size to 50%
        dim.width = 300;
        this.setPreferredSize(dim);




        Border innerBorder = BorderFactory.createTitledBorder("Close account");
        Border outerBorder = BorderFactory.createEmptyBorder(10,10,10,10);

        this.setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        prepareGUI();

    }

    private void prepareGUI(){
        transferFromLabel = new JLabel("Transfer full amount from" );
        transferFromDetails = new JLabel("" + programState.getSelectedbankAccount());
        transferToLabel = new JLabel("Transfer to account: ");

        empty = new JLabel("");

        accountComboBox = new JComboBox();
        accountComboBox.setPreferredSize(new Dimension(200,200));
        for (BankAccount bankAccount :programState.getBankAccounts()){
            accountComboBox.addItem(bankAccount);
        }
        createButton = new JButton("Close");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                  closingAccount = programState.getSelectedbankAccount();

                  transferringAccount = (BankAccount) accountComboBox.getSelectedItem();

                try {
                    closingAccount.closeAccount(transferringAccount);

                    programState.getBankAccounts().remove(closingAccount);
                    System.out.println("closed");
                }catch (NegativeAmountException e1){

                }




                new ClientFrame(programState);



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

        add(transferFromLabel, gc);

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
        gc.anchor = GridBagConstraints.LINE_START;
        add(transferFromDetails, gc);

        gc.gridy = 1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;


        ////////////////////Third row/////////////////
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridy = 2;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(transferToLabel, gc);

        gc.gridy = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;


//  ////////////////////Fourth row/////////////////
        gc.weightx = 0;
        gc.weighty = 0.1;
        gc.gridy = 3;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(accountComboBox,gc);





        ////////////////////5/////////////////
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridy = 4;
        gc.gridx = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
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


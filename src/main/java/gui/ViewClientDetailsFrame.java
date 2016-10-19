package gui;

import bank.BankAccount;
import bank.Client;
import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/06/07.
 */
public class ViewClientDetailsFrame extends JFrame {
    private ProgramState programState;
    private ClientsDetailsPanel clientsDetailsPanel;

    private JPanel fieldsPanel;
    private JPanel buttonPanel;
    private JPanel messagePanel;
    private JLabel clientNameLabel;
    private JComboBox clientNameCombo;
    private JButton searchButton;
    private JButton backButton;

    public ViewClientDetailsFrame(ProgramState programState) {
        super();
        this.setSize(new Dimension(600, 450));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.programState = programState;
        prepareGUI();
    }
    public void prepareGUI() {
        this.setTitle("Search for Client Details");
        this.setLayout(new GridLayout(4, 1));

        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(1, 1));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));

        messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(1, 1));

        clientsDetailsPanel = new ClientsDetailsPanel();

        clientNameLabel = new JLabel("Client Name: ");
        clientNameCombo = new JComboBox();
        for (Client client :programState.getClientList()){
            clientNameCombo.addItem(client);
        }

        fieldsPanel.add(clientNameLabel);
        fieldsPanel.add(clientNameCombo);

        //clientsDetailsPanel = new ClientsDetailsPanel(programState.getClient());
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {


                Client client = (Client) clientNameCombo.getSelectedItem();
                clientsDetailsPanel.setClient((Client) clientNameCombo.getSelectedItem());



               // clientsDetailsPanel.setClient((Client) clientNameCombo.getSelectedItem());
            }
        });

        //loop through each account in list
        //if the input( account number). equals to value of account,getAccountNumber
        //run the method inside that account and
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new MenuFrame(programState);
                ViewClientDetailsFrame.this.dispose();
            }
        });
        buttonPanel.add(backButton);
        buttonPanel.add(searchButton);

        messagePanel.add(clientsDetailsPanel);


        this.add(fieldsPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(messagePanel, BorderLayout.SOUTH);

        this.setVisible(true);

    }

    }

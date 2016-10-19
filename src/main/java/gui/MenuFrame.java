package gui;

import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/06/04.
 */
public class MenuFrame extends JFrame {
    private ProgramState programState;
    private JPanel buttonPanel;
    private JPanel titlePanel;
    private JButton addClientButton;
    private JButton loadSaveButton;
    private JButton transactionsButton;
    private JButton clientDetailsButton;
    private JButton addAccountButton;
    private JButton viewAccountDetails;
    private JButton closeAccountButton;
    private JButton endMonthButton;


    public MenuFrame(ProgramState programState) {
        super();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 450));
        this.programState = programState;
        prepareGUI();
    }
    private void prepareGUI(){
        this.setTitle("Main Menu");
        this.setLayout(new GridLayout(2,1));

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1));

        addClientButton = new JButton("Add Client");
        addClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new CreateClientFrame(programState);
                MenuFrame.this.dispose();
            }
        });

        loadSaveButton = new JButton("Load/Save Files");
        loadSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new SaveLoadFileFrame(programState);
                MenuFrame.this.dispose();
            }
        });



        clientDetailsButton = new JButton("Client Details");
        clientDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewClientDetailsFrame(programState);
                MenuFrame.this.dispose();
            }
        });

        addAccountButton = new JButton("Add new Account");
        addAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new AddBankAccountFrame(programState);
                MenuFrame.this.dispose();
            }
        });
        titlePanel.add(new JLabel("Welcome to UBank"));

        viewAccountDetails = new JButton("View Account Details");
        viewAccountDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewAccountDetailsFrame(programState);
                MenuFrame.this.dispose();
            }
        });

        transactionsButton = new JButton("Withdraw/Deposit");
        transactionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new WithdrawDepositFrame(programState);
                MenuFrame.this.dispose();
            }
        });
         closeAccountButton = new JButton("Close Account");
        closeAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CloseAccountFrame(programState);
                MenuFrame.this.dispose();
            }
        });

        endMonthButton = new JButton("End Month");
        endMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EndMonthFrame(programState);
                MenuFrame.this.dispose();
            }
        });
        buttonPanel.add(viewAccountDetails);
        buttonPanel.add(clientDetailsButton);
        buttonPanel.add(addAccountButton);
        buttonPanel.add(loadSaveButton);
        buttonPanel.add(addClientButton);
        buttonPanel.add(transactionsButton);
        buttonPanel.add(closeAccountButton);
        buttonPanel.add(endMonthButton);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel,BorderLayout.CENTER);
        this.setVisible(true);


    }
}

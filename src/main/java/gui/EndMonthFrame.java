package gui;

import bank.BankAccount;
import exception.NegativeAmountException;
import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/06/09.
 */
public class EndMonthFrame extends JFrame {
    private ProgramState programState;
    private JPanel buttonPanel;
    private JPanel messagePanel;

    private JButton backButton;
    private JButton endMonthButton;


    public EndMonthFrame(ProgramState programState) {
        super();
        this.setSize(new Dimension(600, 450));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.programState = programState;
        prepareGUI();
    }

    public void prepareGUI() {
        this.setTitle("End the Month");
        this.setLayout(new GridLayout(2, 1));


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame(programState);
                EndMonthFrame.this.dispose();
            }
        });

        endMonthButton = new JButton("End The Month");
        endMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (BankAccount account : programState.getBankAccounts()){

                    try {
                        account.endMonth();
                    } catch (NegativeAmountException e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });

        messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(1, 1));

        buttonPanel.add(backButton);
        buttonPanel.add(endMonthButton);

        this.add(buttonPanel,BorderLayout.NORTH);
        this.add(messagePanel,BorderLayout.SOUTH);

        this.setVisible(true);
    }
}

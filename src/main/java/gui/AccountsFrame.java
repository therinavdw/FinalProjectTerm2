package gui;

import bank.BankAccount;
import bank.Client;
import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BandCombineOp;
import java.io.*;

/**
 * Created by therina on 2016/10/21.
 */
public class AccountsFrame extends JFrame {
    private ProgramState programState;
    private JFileChooser importFileChooser;
    private JFileChooser exportFileChooser;
    private JLabel bankAccountLabel;
    private BankAccount bankAccount;
    private JButton backButton;
    private JLabel bankAccountBalance;


    private ClientFrame clientFrame;

    private JPanel toolbar;
    private WithdrawOrDepositForm withdrawOrDepositForm;
    private CloseAccountForm closeAccountForm;

    public  AccountsFrame(ProgramState programState){
        super();
        this.programState = programState;
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(createMenuBar());

        prepareGUI();

    }

    private void  prepareGUI(){
        this.setTitle("Account Details");
        setLayout(new BorderLayout());

        closeAccountForm = new CloseAccountForm(programState);

        importFileChooser = new JFileChooser();
        exportFileChooser = new JFileChooser();


        withdrawOrDepositForm = new WithdrawOrDepositForm(programState);


        ////////////////////toolbar/////////////////////
        toolbar = new JPanel(new BorderLayout(5,5));
        toolbar.setBorder(BorderFactory.createEtchedBorder());
        bankAccountLabel = new JLabel();
        bankAccountBalance = new JLabel();
        backButton = new JButton("Back");


        toolbar.add(bankAccountLabel, BorderLayout.WEST);
        toolbar.add(bankAccountBalance,BorderLayout.SOUTH);
        toolbar.add(backButton, BorderLayout.EAST);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientFrame = new ClientFrame(programState);
                AccountsFrame.this.dispose();
            }
        });
        add(toolbar,BorderLayout.NORTH);
        add(closeAccountForm,BorderLayout.WEST);
        add(withdrawOrDepositForm,BorderLayout.EAST);

        setVisible(true);
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");

        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(AccountsFrame.this,"Are you sure you want to exit?","Confirm exit",JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(importFileChooser.showOpenDialog(AccountsFrame.this) == JFileChooser.APPROVE_OPTION){

                    int action = JOptionPane.showConfirmDialog(AccountsFrame.this,"Are you sure you want to import/load?","Confirm import",JOptionPane.OK_CANCEL_OPTION);
                    if (action == JOptionPane.OK_OPTION) {
                        importFileChooser.getSelectedFile();
                        File file = importFileChooser.getSelectedFile();

                        try {
                            //Deserialize from a file
                            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                            //deserialize the object
                            ProgramState loadedState = (ProgramState) in.readObject();
                            programState = loadedState;
                            in.close();
                        }catch (FileNotFoundException ex){
                            System.out.println("File not found");
                        }catch (IOException ex){
                            System.out.println("I/O issue");
                        }catch (ClassNotFoundException ex){
                            System.out.println("Class not found");
                        }
                    }
                }
            }
        });

        exportDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(exportFileChooser.showOpenDialog(AccountsFrame.this) == JFileChooser.APPROVE_OPTION) {

                    int action = JOptionPane.showConfirmDialog(AccountsFrame.this, "Are you sure you want to export/save?", "Confirm export", JOptionPane.OK_CANCEL_OPTION);
                    if (action == JOptionPane.OK_OPTION) {
                        exportFileChooser.getSelectedFile();
                        File file = exportFileChooser.getSelectedFile();

                        try {
                            //deserialize form a file
                            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                            //deserialize the object
                            out.writeObject(programState);
                            out.close();
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found.");
                        } catch (IOException ex) {
                            System.out.println("I/O issue");
                        }
                    }
                }
            }
        });



        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);


        menuBar.add(fileMenu);


        return menuBar;

    }

    public void setBankAccount(BankAccount bankAccount){
        this.bankAccount = bankAccount;
        refresh();



    }

    public void refresh(){
        bankAccountLabel.setText("Bank account number:  " + bankAccount.getAccountNum().toString());
        bankAccountBalance.setText("Balance:" + bankAccount.getBalance());

    }
}

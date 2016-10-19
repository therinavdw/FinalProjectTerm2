package gui;

import bank.BankAccount;
import bank.Client;
import javafx.scene.control.ComboBox;
import main.ProgramState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.Normalizer;

/**
 * Created by therina on 2016/10/03.
 */
public class ClientFrame extends JFrame {
    private ProgramState programState;
    private AccountFormPanel accountFormPanel;
    private BankAccount bankAccount;


    private JPanel accountFromPanel;
//    private JTextArea textArea;



    private JPanel detailsPanel;
    private JComboBox accountsCombo;
    private JTextField accountDetails;

    private JFileChooser importFileChooser;
    private JFileChooser exportFileChooser;
    private JTextField clientNumberField;
    private Client client;



    private JPanel toolbar;
    private JLabel nameLabel;


    public ClientFrame(ProgramState programState) {
        super();
        this.programState = programState;
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(createMenuBar());

        prepareGUI();

    }





    private void prepareGUI(){
        this.setTitle("Cliet Details");
//        setLayout(new BorderLayout());
//        textArea = new JTextArea();



        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        detailsPanel.setPreferredSize(new Dimension(50, 40));
        detailsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        accountsCombo = new JComboBox();
        for (BankAccount bankAccount :programState.getBankAccounts()){
            accountsCombo.addItem(bankAccount);
        }
        accountsCombo.setPreferredSize(new Dimension(300,300));


        toolbar = new JPanel();
        accountFromPanel = new AccountFormPanel(programState);

        accountDetails = new JTextField();

        importFileChooser = new JFileChooser();
        exportFileChooser = new JFileChooser();

        nameLabel = new JLabel();

        toolbar.add(nameLabel);
        toolbar.setBorder(BorderFactory.createEtchedBorder());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        detailsPanel.add(accountsCombo, c);
        c.gridx= 1;
        c.gridy=1;
        detailsPanel.add(accountDetails, c);

        add(accountFromPanel,BorderLayout.WEST);
        add(toolbar,BorderLayout.NORTH);
//        add(textArea,BorderLayout.CENTER);
        add(detailsPanel,BorderLayout.CENTER);




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
                int action = JOptionPane.showConfirmDialog(ClientFrame.this,"Are you sure you want to exit?","Confirm exit",JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(importFileChooser.showOpenDialog(ClientFrame.this) == JFileChooser.APPROVE_OPTION){

                    int action = JOptionPane.showConfirmDialog(ClientFrame.this,"Are you sure you want to import/load?","Confirm import",JOptionPane.OK_CANCEL_OPTION);
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
                if(exportFileChooser.showOpenDialog(ClientFrame.this) == JFileChooser.APPROVE_OPTION) {

                    int action = JOptionPane.showConfirmDialog(ClientFrame.this, "Are you sure you want to export/save?", "Confirm export", JOptionPane.OK_CANCEL_OPTION);
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

    public void setClient(Client client){
        this.client = client;
        nameLabel.setText("Name:  " + client.getClientName().toString());


    }





}

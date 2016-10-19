package gui;



import bank.Client;
import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by therina on 2016/10/02.
 */
public class HomeFrame extends JFrame {
    private ProgramState programState;
    private JTextArea textArea;
    private FormPanel formPanel;
    private JFileChooser importFileChooser;
    private JFileChooser exportFileChooser;
    private AccountFormPanel accountFormPanel;


    private JPanel toolbar;
    private JComboBox clientCombo;
    private JButton searchButton;
    private Client client;
    private ClientFrame clientFrame;


    public HomeFrame(ProgramState programState){
        super();
        this.programState = programState;
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(createMenuBar());
        prepareGUI();

    }


    private void prepareGUI(){
        this.setTitle("Home Frame");
        setLayout(new BorderLayout());
        toolbar = new JPanel();
        textArea = new JTextArea();
        formPanel = new FormPanel(programState);

        importFileChooser = new JFileChooser();
        exportFileChooser = new JFileChooser();


        ////////////////////toolbar/////////////////////
    toolbar.setBorder(BorderFactory.createEtchedBorder());

        clientCombo = new JComboBox();
        for (Client client :programState.getClientList()){
            clientCombo.addItem(client);
        }

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client client = (Client) clientCombo.getSelectedItem();
                programState.setSelectedClient(client);
                clientFrame = new ClientFrame(programState);
                clientFrame.setClient(client);

                HomeFrame.this.dispose();

//                homeFrame.setClient((Client) clientCombo.getSelectedItem());
            }
        });





        toolbar.add(clientCombo);
        toolbar.add(searchButton);

        add(toolbar,BorderLayout.NORTH);
        add(formPanel,BorderLayout.WEST);
        add(textArea,BorderLayout.CENTER);


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
                int action = JOptionPane.showConfirmDialog(HomeFrame.this,"Are you sure you want to exit?","Confirm exit",JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(importFileChooser.showOpenDialog(HomeFrame.this) == JFileChooser.APPROVE_OPTION){

                    int action = JOptionPane.showConfirmDialog(HomeFrame.this,"Are you sure you want to import/load?","Confirm import",JOptionPane.OK_CANCEL_OPTION);
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
                if(exportFileChooser.showOpenDialog(HomeFrame.this) == JFileChooser.APPROVE_OPTION) {

                    int action = JOptionPane.showConfirmDialog(HomeFrame.this, "Are you sure you want to export/save?", "Confirm export", JOptionPane.OK_CANCEL_OPTION);
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
}

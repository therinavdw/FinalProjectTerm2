package gui;

import bank.Client;
import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/06/01.
 */
public class CreateClientFrame extends JFrame {
    private ProgramState programState;
    private JPanel buttonPanel;
    private JPanel fieldsPanel;

    private JLabel clientNameLabel;
    private JLabel clientNumberLabel;
    private JLabel clientEmailLabel;

    private JTextField clientNameField;
    private JTextField clientNumberField;
    private JTextField clientEmailField;

    private JButton addClientButton;
    private JButton backButton;


    public CreateClientFrame(ProgramState programState){
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600,450));
        this.programState = programState;
        prepareGUI();
    }
        public void prepareGUI(){
            this.setTitle("Create new client");
            this.setLayout(new GridLayout(2,1));

            fieldsPanel = new JPanel();
            fieldsPanel.setLayout(new GridLayout(4,2));

            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1,2));

            clientNameLabel = new JLabel("Client Name: ");
            clientNumberLabel = new JLabel("Client Number: ");
            clientEmailLabel = new JLabel("Client Email: ");

            clientNameField = new JTextField();
            clientNumberField = new JTextField();
            clientEmailField = new JTextField();

            addClientButton = new JButton("Create");
            addClientButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    Client client = null;

                    client = new Client(new String(clientNameField.getText()), new Long(clientNumberField.getText()),clientEmailField.getText());
                    programState.getClientList().add(client);

                    JOptionPane.showMessageDialog(CreateClientFrame.this, "Success! A new client is added","Added client message",
                            JOptionPane.INFORMATION_MESSAGE);
                    new MenuFrame(programState);
                    CreateClientFrame.this.dispose();
                }
            });

            backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    new MenuFrame(programState);
                    CreateClientFrame.this.dispose();
                }
            });
            fieldsPanel.add(clientNameLabel);
            fieldsPanel.add(clientNameField);
            fieldsPanel.add(clientEmailLabel);
            fieldsPanel.add(clientEmailField);
            fieldsPanel.add(clientNumberLabel);
            fieldsPanel.add(clientNumberField);

            buttonPanel.add(addClientButton);
            buttonPanel.add(backButton);

            this.add(fieldsPanel,BorderLayout.NORTH);
            this.add(buttonPanel,BorderLayout.CENTER);

            this.setVisible(true);

        }
}

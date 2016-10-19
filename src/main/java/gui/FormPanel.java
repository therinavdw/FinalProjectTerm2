package gui;

import bank.Client;
import main.ProgramState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/10/02.
 */
public class FormPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel numberLabel;
    private JButton createButton;
    private JTextField clientEmailField;
    private JTextField clientNameField;
    private JTextField clientNumberField;
    private ProgramState programState;

    public FormPanel(ProgramState programState){
        super();
        this.programState = programState;
        Dimension dim = getPreferredSize();
        //set size to 50%
        dim.width = 250;
        this.setPreferredSize(dim);




        Border innerBorder = BorderFactory.createTitledBorder("Add Client");
        Border outerBorder = BorderFactory.createEmptyBorder(10,10,10,10);

        this.setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        prepareGUI();
    }

    private void prepareGUI(){
        nameLabel = new JLabel("Name: ");
        emailLabel = new JLabel("Email: ");
        numberLabel = new JLabel("Number: ");
        clientNameField = new JTextField(10);
        clientEmailField = new JTextField(10);
        clientNumberField = new JTextField(10);

        createButton = new JButton("Create");

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client client = null;

                client = new Client(new String(clientNameField.getText()), new Long(clientNumberField.getText()),clientEmailField.getText());
                programState.getClientList().add(client);

                JOptionPane.showMessageDialog(FormPanel.this, "Success! A new client is added","Added client message",
                        JOptionPane.INFORMATION_MESSAGE);
                new HomeFrame(programState);

                System.out.println(client);
            }
        });

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //space of cell



        ////////////////////first row/////////////////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        //take up all space or not
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;

        add(nameLabel,gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(clientNameField,gc);

        ////////////////////second row/////////////////
        gc.weightx = 1;
        gc.weighty = 0.1;
        //add new row
        gc.gridy = 1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(emailLabel,gc);

        gc.gridy = 1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(clientEmailField,gc);

        ////////////////////Third row/////////////////
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridy = 2;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(numberLabel,gc);

        gc.gridy = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(clientNumberField,gc);

        ////////////////////fourth row/////////////////
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridy = 3;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(createButton,gc);





    }
}

package gui;


import bank.Client;
import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by therina on 2016/10/02.
 */
public class Toolbar extends JPanel {
    private ProgramState programState;
    private JComboBox clientCombo;
    private JButton searchButton;
    private Client client;
    private JButton backButton;

    public  Toolbar(ProgramState programState){
        super();
        this.programState = programState;
//        this.setBorder(BorderFactory.createLineBorder(Colour.Black));




        prepareGUI();


    }

    public void prepareGUI(){
        this.setBorder(BorderFactory.createEtchedBorder());

        clientCombo = new JComboBox();
        for (Client client :programState.getClientList()){
            clientCombo.addItem(client);
        }

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client client = (Client) clientCombo.getSelectedItem();
//
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });





        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(clientCombo);
        add(searchButton);

    }
}

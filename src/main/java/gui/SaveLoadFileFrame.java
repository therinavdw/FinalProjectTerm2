package gui;

import main.ProgramState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by therina on 2016/06/04.
 */
public class SaveLoadFileFrame extends JFrame {
    private ProgramState programState;
    private JPanel formPanel;
    private JPanel savePanel;
    private JPanel backPanel;

    private  JFileChooser loadFileChooser;
    private JFileChooser saveFileChooser;

    private JButton loadButton;
    private JButton launchLoadChooserButton;
    private JButton saveButton;
    private JButton launchSaveChooserButton;



    private JButton backButton;
    private JPanel loadPanel;


    public SaveLoadFileFrame(ProgramState programState) {
        super();
        this.setSize(new Dimension(600, 450));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.programState = programState;
        prepareGUI();
    }

    public void prepareGUI(){
        this.setTitle("Load/Save Menu");
        this.setLayout(new GridLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new MenuFrame(programState);
                SaveLoadFileFrame.this.dispose();
            }
        });
        backPanel = new JPanel();
        backPanel.add(backButton);


        saveFileChooser = new JFileChooser();
        launchSaveChooserButton = new JButton("Choose save button");
        launchSaveChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                saveFileChooser.showOpenDialog(SaveLoadFileFrame.this);
            }
        });
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                saveFileChooser.getSelectedFile();
                File file = saveFileChooser.getSelectedFile();

                try {
                    //deserialize form a file
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    //deserialize the object
                    out.writeObject(programState);
                    out.close();
                }catch (FileNotFoundException ex){
                    System.out.println("File not found.");
                }catch (IOException ex){
                    System.out.println("I/O issue");
                }
            }
        });
        savePanel = new JPanel();
        savePanel.add(launchSaveChooserButton);
        savePanel.add(saveButton);

        loadFileChooser = new JFileChooser();
        launchLoadChooserButton = new JButton("Chooses Load Button");
        launchLoadChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                loadFileChooser.showOpenDialog(SaveLoadFileFrame.this);
            }
        });
        loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                loadFileChooser.getSelectedFile();
                File file = loadFileChooser.getSelectedFile();

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
        });
        loadPanel = new JPanel();
        loadPanel.add(launchLoadChooserButton);
        loadPanel.add(loadButton);

        this.add(loadPanel);
        this.add(savePanel);
        this.add(backPanel);

        this.setVisible(true);
    }
}

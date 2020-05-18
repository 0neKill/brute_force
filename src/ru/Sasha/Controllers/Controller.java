/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Sasha.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author Sasha
 */
public class Controller implements Initializable {
    private Helpers help;
    @FXML
    private TextField userName;
    @FXML
    private Label passLabel;
    @FXML
    private Pane MainBlock;
    @FXML
    private MenuButton wordsBox;
    @FXML
    private Label labelLoad;
    private String word =null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Spinner spn = new Spinner(MainBlock);
        spn.start();
        labelLoad.setText("Загрузка словаря...");
        help = new Helpers();
        help.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                spn.interrupt();
                Platform.runLater(() -> {
                    labelLoad.setText(String.valueOf(""));
                });
            }
        }).start();
    }  
    @FXML
    void oneWords(ActionEvent event) {
        wordsBox.setText("Words1");
        word = "Words1";
    }

    @FXML
    void twoWords(ActionEvent event) {
        wordsBox.setText("Words2");
        word = "Words2";
    }

    @FXML
    void threeWords(ActionEvent event) {
        wordsBox.setText("Words3");
        word = "Words3";
    }
        
    @FXML
    void exitEvent_btn(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void resultBtnAction(ActionEvent event) { 
        if(word==null) {
            JOptionPane.showMessageDialog(null, "Выберите словарь");
            return;
        }
        if(userName.getText().trim().length()==0) {
            System.err.println(userName.getText().length()==0);
            JOptionPane.showMessageDialog(null, "Введите хэш");
            return;
        }
        passLabel.setText("");
        new Thread(new BruteForce("./"+word,userName.getText().trim(),MainBlock,passLabel)).start();
    }
}

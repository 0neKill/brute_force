/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Sasha.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;


/**
 *
 * @author Sasha
 */
public class BruteForce extends Thread{
    
    private String path = null;
    private String hash = null;    
    private Pane spn = null;
    private Label passLabel=null;

    
    public BruteForce(String path,String hash,Pane spn,Label passLabel){
        this.path = path;
        this.hash = hash;
        this.spn = spn;
        this.passLabel = passLabel;
    }
    
    @Override
    public void run() {         
        try {
            init();
        } catch (FileNotFoundException | NoSuchAlgorithmException ex) {
            Logger.getLogger(BruteForce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void init() throws FileNotFoundException, NoSuchAlgorithmException {
        Spinner s = new Spinner(spn);
        s.start();
        File file = new File(path);
        try (Scanner scan = new Scanner(file)) {
            MessageDigest sha;
            sha = MessageDigest.getInstance("SHA-256");
            String str;
            boolean res;
            while (scan.hasNext()) {
                str = scan.nextLine();
                byte bytes[] = sha.digest(str.getBytes());
                res =checkHash(bytes);
                if(res) {
                    final String k = str;
                    Platform.runLater(() -> {
                        passLabel.setText(String.valueOf(k));
                    });
                    s.interrupt();
                    break;
                }
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Словарь не найден!");
            s.interrupt();
        }
    }
    private boolean checkHash(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for(byte b : bytes) {
            builder.append(String.format("%02X", b));
        }
        return builder.toString().toLowerCase().equals(hash);
    }
}

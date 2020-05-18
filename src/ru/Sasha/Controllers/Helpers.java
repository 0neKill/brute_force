/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Sasha.Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sasha
 */
public class Helpers extends Thread{
    @Override
    public void run() {
        try {
            createArray();
        } catch (IOException ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void createArray() throws IOException {
        File file = new File("./Words1");
        FileWriter fw = new FileWriter(file);
        String result;
        for(char c1 = 'A'; c1 <= 'Z'; c1++){
            for(char c2 = 'A'; c2 <= 'Z'; c2++){
                for(char c3 = 'A'; c3 <= 'Z'; c3++){
                    for(char c4 = 'A'; c4 <= 'Z'; c4++){
                        for(char c5 = 'A'; c5 <= 'Z'; c5++){
                             result = parseString( c1 ,c2 , c3,c4,c5);
                              fw.append(result.toLowerCase() + "\n");
                        }
                    }
                }
            }          
        }
    }
    private String parseString(char c1,char c2,char c3,char c4,char c5)  {
        String str ="";
        str = Character.toString(c1) + Character.toString(c2)+Character.toString(c3)+Character.toString(c4)+Character.toString(c5);
        return str;
    }
}

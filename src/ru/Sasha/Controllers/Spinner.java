/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Sasha.Controllers;

import javafx.application.Platform;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;

/**
 *
 * @author Sasha
 */
public class Spinner extends Thread{
    private Pane MainBlock;    

    public  Spinner(Pane MainBlock) {
        this.MainBlock = MainBlock;
    }
    @Override
    public void run() {
        ProgressIndicator c = new ProgressIndicator();
        Platform.runLater(() -> {
            MainBlock.getChildren().add(c);
         });
        while (!interrupted()) {
            c.setProgress(-1f);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            MainBlock.getChildren().remove(c);
            }
        });       
    }
}

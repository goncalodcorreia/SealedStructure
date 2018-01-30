package com.sealedstructure.gui;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class BunkersGUI {
    private Stage mainStage;

    public Stage display(){
        mainStage = new Stage();
        mainStage.setTitle("Bunkers");

        /*Layout*/
        HBox mainLayout = new HBox();

        /*Left Layout* - Will Display a list of Bunkers*/
        VBox leftLayout = new VBox();
        //ReadFile


        /*Right Layout* - Should only display when a button is clicked, and should show a list of files inside a bunker*/


        /*Scene*/
        Scene mainScene = new Scene(mainLayout,800,1200);
        mainStage.setScene(mainScene);
        mainStage.show();

        return mainStage;
    }

}

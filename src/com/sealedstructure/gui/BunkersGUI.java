package com.sealedstructure.gui;

import com.sealedstructure.functionality.Bunker;
import com.sealedstructure.functionality.IOFunctions;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class BunkersGUI {
    private Stage mainStage;
    private ArrayList<Button> buttons;


    public Stage display(){
        mainStage = new Stage();
        buttons = new ArrayList<>();
        mainStage.setTitle("Bunkers");


        /*Layout*/
        BorderPane mainLayout = new BorderPane();
        /*Center Layout*/
        HBox centerLayout = new HBox();

        /*Left Layout* - Will Display a list of Bunkers*/
        VBox leftLayout = new VBox();

        for(int i = 0; i < 6; i++){
            IOFunctions.IO_FUNCTIONS_INSTANCE.writeObjectFile(new Bunker(""+i,"randddd"),"bunkers.txt");
        }

        for(Object o : IOFunctions.IO_FUNCTIONS_INSTANCE.readMultipleObjectsFile("bunkers.txt")){
            System.out.println("Adding" + o);
            leftLayout.getChildren().add(new Button(((Bunker)o).getName()));
        }

        /*Right Layout* - Should only display when a button is clicked, and should show a list of files inside a bunker*/
        VBox rightLayout = new VBox();



        /*Bottom Layout*/
        HBox bottomLayout = new HBox();
        Button addBunker = new Button("Add");
        bottomLayout.getChildren().add(addBunker);

        addBunker.setOnAction(null);

        centerLayout.getChildren().addAll(leftLayout,rightLayout);
        mainLayout.setCenter(centerLayout);
        mainLayout.setBottom(bottomLayout);

        /*Scene*/
        Scene mainScene = new Scene(mainLayout,800,1000);
        mainStage.setScene(mainScene);
        mainStage.show();

        return mainStage;
    }

}

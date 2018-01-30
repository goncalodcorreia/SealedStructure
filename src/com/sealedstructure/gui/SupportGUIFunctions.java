package com.sealedstructure.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SupportGUIFunctions {

    public static final SupportGUIFunctions SUPPORT_GUI_FUNCTIONS = new SupportGUIFunctions();
    public static final int ALERT_WINDOW_WIDTH = 600;
    public static final int ALERT_WINDOW_HEIGHT = 100;



    public void customPrompt(String title,String text){
        Stage customPromptStage = new Stage();

        customPromptStage.initModality(Modality.APPLICATION_MODAL);
        customPromptStage.setTitle(title);

        Label promptL = new Label(text);
        Button closeB = new Button("Close");

        /*Layout*/
        VBox customLayout = new VBox();
        customLayout.getChildren().addAll(promptL,closeB);
        customLayout.setAlignment(Pos.CENTER);



        /*Handle Events*/
        closeB.setOnAction(e->customPromptStage.close());

        /*Scene*/
        Scene customScene = new Scene(customLayout,ALERT_WINDOW_WIDTH,ALERT_WINDOW_HEIGHT);
        customPromptStage.setScene(customScene);
        customPromptStage.showAndWait();



    }
}

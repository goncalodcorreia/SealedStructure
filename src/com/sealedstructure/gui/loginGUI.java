package com.sealedstructure.gui;

import com.sealedstructure.functionality.Config;
import com.sealedstructure.functionality.Generator;
import com.sealedstructure.functionality.IOFunctions;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import java.io.IOException;


public class loginGUI extends Application implements EventHandler<ActionEvent> {
    private boolean registered;
    private boolean logged;
    private Stage currentStage;
    private Button aboutB;
    private Button backB;
    private Button loginB;
    private String password;
    private PasswordField pwInput;
    private Config conf;


    public static void main(String[] args) {
        launch(args);
    }

    /*Function that will launch the first stage*/
    public void start(Stage primaryStage) {

        /*Check if this is the first login*/
        conf = (Config) IOFunctions.IO_FUNCTIONS_INSTANCE.readObjectFile("config.dat");

        registered = (conf != null);

        if(!registered) {
            firstTimeSetup();
        }

        /*After the prompt, show login window*/

        currentStage = primaryStage;
        currentStage.setTitle("SealedStructure V1.0");
        currentStage.setScene(setMainWindow());
        currentStage.setResizable(false);
        currentStage.show();


    }

    /*This is meant to be the main prompt, for the password to log in the whole system*/
    public Scene setMainWindow() {
        Label welcomeL = new Label("Welcome to SealedStructure v1.0");
        Label passwordL = new Label("Password: ");
        Button forgotPasswordB = new Button("Forgot Password? ");
        aboutB = new Button("About this program");
        pwInput = new PasswordField();
        loginB = new Button("Login");


        /*Layout*/
        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(forgotPasswordB, aboutB,loginB);
        buttonLayout.setSpacing(10);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(welcomeL, passwordL, pwInput, buttonLayout);

        /*ButtonHandling*/
        aboutB.setOnAction(this);
        loginB.setOnAction(this);

        /*KeyEventHandling* - Overloading handle method didn't work*/
        pwInput.setOnKeyPressed(e-> {
            if (e.getCode() == KeyCode.ENTER){
                checkPassword(pwInput.getText());
            }
        });

        Scene mainScene = new Scene(layout, 400, 150);
        return mainScene;


    }

    /*About Section - Will Essentially read a Log File and display it on the TArea*/
    public Scene setAboutWindow() {
        backB = new Button("Back");
        TextArea aboutTA = new TextArea();
        aboutTA.setEditable(false);
        aboutTA.setPrefRowCount(20);
        loadText(aboutTA,"about.txt");

        VBox aboutLayout = new VBox();
        aboutLayout.getChildren().addAll(aboutTA,backB);
        backB.setAlignment(Pos.BASELINE_LEFT);

        backB.setOnAction(this);


        Scene aboutScene = new Scene(aboutLayout, 300, 300);

        return aboutScene;
    }

    /*Function that handles all input events from the user, depending on the source*/
    public void handle(ActionEvent e) {
        if (e.getSource() == aboutB) {
            currentStage.setScene(setAboutWindow());
        }else if (e.getSource() == backB) {
            currentStage.setScene(setMainWindow());
        }else if(e.getSource() == loginB){
            checkPassword(pwInput.getText());
        }

    }

    /*This function will load any file into the TA*/
    private void loadText(TextArea ta,String file){
        String result = IOFunctions.IO_FUNCTIONS_INSTANCE.readFile(file);

        ta.setText(result);
    }

    /*Function that checks if it is the first time the user uses this app or not, essentially giving it its first password*/
    private void firstTimeSetup(){
        /*Vars*/
        Stage ftsStage = new Stage();
        ftsStage.setTitle("First Time Setup");
        Label ftsText = new Label("Welcome to the first time setup! Please answer to the following questions: ");
        Label q1 = new Label("Pet's Name : ");
        Label q2 = new Label("Best Friend's name : ");
        Label q3 = new Label("First School's name : ");
        Label q4 = new Label("Year of Birth : ");

        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        TextField t4 = new TextField();

        Button ftsSubmit = new Button("Submit");


        /* Top Layout*/
        HBox topLayout = new HBox();
        topLayout.getChildren().add(ftsText);
        topLayout.setAlignment(Pos.CENTER);
        topLayout.setSpacing(10);

        /*Mid Layout*/
        GridPane gp = new GridPane();
        gp.add(q1,0,0);
        gp.add(q2,0,1);
        gp.add(q3,0,2);
        gp.add(q4,0,3);

        gp.add(t1,1,0);
        gp.add(t2,1,1);
        gp.add(t3,1,2);
        gp.add(t4,1,3);

        gp.setAlignment(Pos.CENTER);

        ftsSubmit.setOnAction(e->{
            String v1 = t1.getText();
            String v2 = t2.getText();
            String v3 = t3.getText();
            if (!v1.isEmpty() && !v2.isEmpty() && !v3.isEmpty()){
                try {
                    int year = Integer.parseInt(t4.getText());
                    conf = new Config(year, v1, v2, v3);
                    IOFunctions.IO_FUNCTIONS_INSTANCE.writeObjectFile(conf,"config.dat");
                    ftsStage.close();
                } catch (NumberFormatException er) {
                    SupportGUIFunctions.SUPPORT_GUI_FUNCTIONS.customPrompt("ERROR", "Please insert a valid Year of Birth");
                    er.printStackTrace();
                }
            }else{
                SupportGUIFunctions.SUPPORT_GUI_FUNCTIONS.customPrompt("ERROR", "Please Insert Valid Answers!");
            }

        });

        /* Bot Layout*/
        HBox botLayout = new HBox();
        botLayout.getChildren().add(ftsSubmit);
        botLayout.setAlignment(Pos.CENTER);

        /*Global Layout*/
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(topLayout,gp,botLayout);

        /*Scene Settings*/
        Scene ftsScene = new Scene(mainLayout,600,200);
        ftsStage.setScene(ftsScene);
        ftsStage.showAndWait();


        /*Prompt Showing the password for the first (and only) time!*/
        String passwordMessage = conf.getPassword();
        SupportGUIFunctions.SUPPORT_GUI_FUNCTIONS.customPrompt("First Time Setup","Welcome! Please note your first password, you won't see it again! "  +"\n\n" +  passwordMessage);
        registered = true;
    }

    /*Function that checks the Password and opens the Bunker list if it is correct*/
    private void checkPassword(String s){
        if(s.equals(conf.getPassword())) {
            currentStage.close();
            currentStage = new BunkersGUI().display();
        }else{
            SupportGUIFunctions.SUPPORT_GUI_FUNCTIONS.customPrompt("ERROR","Wrong Password! please try again!");
        }
    }

}


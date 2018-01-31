package com.sealedstructure.functionality;

import com.sealedstructure.gui.SupportGUIFunctions;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;

public class Bunker implements Serializable{

    private String name;
    private String key;
    private ArrayList<String> files;


    public Bunker(String name, String key){
        this.name = name;
        this.key = key;
    }

    public Bunker(String name){
        this.name = name;
    }


    public void addFile(String s){
        if(!files.contains(s)){
            files.add(s);
        }else{
            SupportGUIFunctions.SUPPORT_GUI_FUNCTIONS.customPrompt("ERROR","File name is already used in this Bunker");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

}


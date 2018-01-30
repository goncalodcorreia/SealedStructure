package com.sealedstructure.functionality;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IOFunctions {
    public static final IOFunctions IO_FUNCTIONS_INSTANCE = new IOFunctions();
    String filename;
    private Config conf;

    private enum EncryptionLevel{
        EASY,MEDIUM,HARD;
    }


    /*Reads a file and returns it as a string with the original format it has, without any decryption*/
    public ArrayList<String> readFile(String file) {
        ArrayList<String>rawText = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(file));
            while (scan.hasNextLine()){  //Iterate the file.
                rawText.add(scan.nextLine());
            }

        } catch (FileNotFoundException e) { //File that scan tries to open hasn't been found.

        }

        return rawText;
    }

    /*Here the config.dat file is written*/
    public void writeConfigFile(Config file){
        try {
            FileOutputStream fos = new FileOutputStream("config.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(file);


        }catch(FileNotFoundException e) {
            e.printStackTrace();

        }catch(IOException er) {
            er.printStackTrace();
        }

    }

    /*Function to read the Config.dat file*/
    public Config readConfigFile(){
        Config result;
        try{
            FileInputStream fin = new FileInputStream("config.dat");
            ObjectInputStream in = new ObjectInputStream(fin);
            result = (Config)in.readObject();
            return result;
        }catch(FileNotFoundException er){
            er.printStackTrace();
            return null;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }catch(ClassNotFoundException err){
            err.printStackTrace();
            return null;
        }

    }

    /*Function that calls the decryption method, according to the value that's stated in the fileIndex.*/
    public void decryptFile(String file, EncryptionLevel level, String key){
        ArrayList<String> encryptedText = readFile(file);
        String result = "";

        for(String s : encryptedText){
            result +=(s+"\n");
        }

        switch (level){
            case EASY: Generator.getINSTANCE().easyDecrypt(result,key);
            case MEDIUM: Generator.getINSTANCE().mediumDecrypt(result,key);
            case HARD: Generator.getINSTANCE().hardDecrypt(result,key);
        }

    }

    public Config getConf(){
        return conf;
    }
}

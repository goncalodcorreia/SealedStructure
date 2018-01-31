package com.sealedstructure.functionality;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IOFunctions {
    public static final IOFunctions IO_FUNCTIONS_INSTANCE = new IOFunctions();  //Singleton


    private enum EncryptionLevel{
        EASY,MEDIUM,HARD;
    }


    /*Reads a file and returns it as a string with the original format it has, without any decryption*/
    public String readFile(String file) {
        String rawText ="";
        try {
            Scanner scan = new Scanner(new File(file));
            while (scan.hasNextLine()){  //Iterate the file.
                rawText += scan.nextLine() + "/n";
            }

        } catch (FileNotFoundException e) { //File that scan tries to open hasn't been found.

        }

        return rawText;
    }

    /*This is the function used to write any object */
    public void writeObjectFile(Object obj,String file){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);


        }catch(FileNotFoundException e) {
            e.printStackTrace();

        }catch(IOException er) {
            er.printStackTrace();
        }

    }
    /*This allows the user to read multiple objects, this is useful to read bunker files instead of the config file, preserving the readObjectFile function*/
    public ArrayList<Object> readMultipleObjectsFile(String file){
        ArrayList<Object> objs = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(file));
            while (scan.hasNextLine()) {
                objs.add(readObjectFile(scan.nextLine()));
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return objs;
    }

    /*Function to read the Config.dat file*/
    public Object readObjectFile(String file){
        try{
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fin);
            return in.readObject();
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
        switch (level){
            case EASY: Generator.getINSTANCE().easyDecrypt(readFile(file),key);
            case MEDIUM: Generator.getINSTANCE().mediumDecrypt(readFile(file),key);
            case HARD: Generator.getINSTANCE().hardDecrypt(readFile(file),key);
        }

    }

}

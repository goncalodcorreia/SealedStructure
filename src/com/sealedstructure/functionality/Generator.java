package com.sealedstructure.functionality;

import com.sun.org.apache.xpath.internal.SourceTree;
import sun.applet.resources.MsgAppletViewer_zh_TW;

public class Generator {

    private final int CHAR_MIN = 48;
    private final int CHAR_MAX = 126;
    private final int MEDIUM_ENCRYPT_KEYSIZE = 10;
    private final int BINARY_ENCRYPT_KEYSIZE = 7;
    private final static Generator INSTANCE = new Generator();

    private Generator(){} //Private constructor so that new Generators can't be created.


    /*Function to generate all keys, given the size of the key*/
    public String generateKey(int size){
        String result ="";
        for(int x = 0; x < size; x++){
            int value = (int) (Math.random()*(CHAR_MAX-CHAR_MIN))+CHAR_MIN; //Calculates a value between that max and min ASCII Values
            String c = "" + (char)value;
            result += c;
        }
        return result;
    }

    /*Encryption */

    /*Simple Encryption using a random offset for the whole message*/
    public void easyEncrypt(String message){
        String newMessage = "";
        int offset = (int)(Math.random()*(CHAR_MAX-CHAR_MIN))+CHAR_MIN; //calculate offset
        for(int x = 0; x < message.length(); x++){
            char c = message.charAt(x);
            char recalculatedvalue = (char)(c+offset);
            newMessage += recalculatedvalue;
        }

        System.out.println("Initial Message : " + message);
        System.out.println("offset : " +offset);
        System.out.println("Encrypted message : " +newMessage);



    }


    /*Medium Encryption*/
    public void mediumEncrypt(String message){
        String newMessage = "";
        Character[] offsetValues = new Character[MEDIUM_ENCRYPT_KEYSIZE];

        for(int x = 0; x < offsetValues.length; x++){ //Populating the Offset values with random offsets in the ASCII Range, although it's not too relevant.
            offsetValues[x] = (char)((int)(Math.random()*(CHAR_MAX-CHAR_MIN))+CHAR_MIN);
        }

        int counter = 0; // this value will iterate all the offsetValues.

        for(int x = 0; x < message.length(); x++){
            char c = message.charAt(x);
            char recalculatedvalue = (char)(c+offsetValues[counter]);

            newMessage += recalculatedvalue;
            counter++;

            /*Restart the counter*/
            if(counter == MEDIUM_ENCRYPT_KEYSIZE){
                counter = 0;
            }
        }

        System.out.println("Initial Message : " + message);
        System.out.println("offsetKey : " );
        for(Character x : offsetValues){ System.out.print(x + " ");}
        System.out.println("Encrypted message : " +newMessage);
    }

    /*Hard (Binary) Encryption*/

    public void hardEncryption(String message){
        String newMessage = "";
        String uncryptedMessage = "";
        Integer[] binaryOffsetValues = new Integer[BINARY_ENCRYPT_KEYSIZE];

        /*Populate the Array with Binary Digits, this will be the Key*/
        for(int v = 0; v < binaryOffsetValues.length; v++){
            binaryOffsetValues[v] = (int)(Math.random()+0.5);
        }
        System.out.println("Key : " + binaryOffsetValues);

        /*Convert each Character into a Binary Value, and XOR it with the Key*/
        for(int x = 0; x < message.length(); x++){
            String binaryValue = Integer.toBinaryString(message.charAt(x));
            String finalBinaryValues = "";
            System.out.println(binaryValue);

            for(int y = 0; y < binaryValue.length(); y++){
                finalBinaryValues+=Integer.parseInt(binaryValue.charAt(y) +"")^binaryOffsetValues[y];
            }

            newMessage += finalBinaryValues;
            uncryptedMessage += binaryValue;

        }

        System.out.println("Encrypted : " + newMessage);
        System.out.println("Decrypted : " + uncryptedMessage);
    }

    /*Decryption*/

    public void easyDecrypt(String message,String Stringoffset){
        String newMessage = "";
        int offset = Integer.parseInt(Stringoffset);
        for(int x = 0; x < message.length(); x++){
            char c = message.charAt(x);
            char recalculatedvalue = (char)(c-offset);
            newMessage += recalculatedvalue;
        }

        System.out.println("Initial Message : " + message);
        System.out.println("offset : " +offset);
        System.out.println("Encrypted message : " +newMessage);

    }

    public void mediumDecrypt(String message,String key){
        String newMessage = "";
        Character[] offsetValues = new Character[MEDIUM_ENCRYPT_KEYSIZE];

        for(int x = 0; x < offsetValues.length; x++){ //Populating the Offset values with random offsets in the ASCII Range, although it's not too relevant.
            offsetValues[x] = key.charAt(x);
        }

        int counter = 0; // this value will iterate all the offsetValues.

        for(int x = 0; x < message.length(); x++){
            char c = message.charAt(x);
            char recalculatedvalue = (char)(c-offsetValues[counter]);

            newMessage += recalculatedvalue;
            counter++;

            /*Restart the counter*/
            if(counter == MEDIUM_ENCRYPT_KEYSIZE){
                counter = 0;
            }
        }

    }

    public void hardDecrypt(String message,String key) {
    //Divide the message by segments with the same size as the key
    //Calculate ASCII value of that same segment -> Convert into a letter.
    //Add that to the final result.
    }



    public static Generator getINSTANCE(){
        return INSTANCE;
    }

    /*Testing*/
    public static void main(String[] args){
        getINSTANCE().hardEncryption("UMA BOLA DE FUTEBOL");

    }

}

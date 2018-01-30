package com.sealedstructure.functionality;

import java.io.Serializable;

public class Config implements Serializable{
    private static final long serialVersionUID = -1L;
    private int year;
    private String reply1;
    private String reply2;
    private String reply3;
    private String password;


    public Config(int year, String reply1, String reply2, String reply3){
        this.year = year;
        this.reply1 = reply1;
        this.reply2 = reply2;
        this.reply3 = reply3;
        this.password = Generator.getINSTANCE().generateKey(4);

    }

    public boolean verifyConfig(int year, String reply1, String reply2, String reply3){
        if(this.year == year && this.reply1.equalsIgnoreCase(reply1) && this.reply2.equalsIgnoreCase(reply2)&& this.reply3.equalsIgnoreCase(reply3)){
            return true;
        }

        return false;

    }


    public String toString(){
        return "Config File Pw :" + this.password;
    }

    public int getYear() {
        return year;
    }

    public String getReply1() {
        return reply1;
    }

    public String getReply2() {
        return reply2;
    }

    public String getReply3() {
        return reply3;
    }

    public String getPassword(){return password;}

    public void setYear(int year) {
        this.year = year;
    }

    public void setReply1(String reply1) {
        this.reply1 = reply1;
    }

    public void setReply2(String reply2) {
        this.reply2 = reply2;
    }

    public void setReply3(String reply3) {
        this.reply3 = reply3;
    }

}

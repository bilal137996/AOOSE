/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.util.ArrayList;

/**
 *
 * @author Bilal
 */
public class Complains {
    int ID;
    String Message,Type ,TimeStamp , ComplainSender;
    public static ArrayList<Complains> AllComplains;
    

    public Complains( String Message, String Type) {
        
        this.Message = Message;
        this.Type = Type;
     
    }

    public String getComplainSender() {
        return ComplainSender;
    }

    public void setComplainSender(String ComplainSender) {
        this.ComplainSender = ComplainSender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }
    
 
    
}

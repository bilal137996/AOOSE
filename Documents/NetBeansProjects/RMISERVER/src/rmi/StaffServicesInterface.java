/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Bilal
 */
public interface StaffServicesInterface  extends Remote{
        // public <T> T  Loign(T instance,String username, String password) throws RemoteException;
   
   public Object Loign(Object Param,String username, String password) throws RemoteException;
     public Transactions ViewTransactionsHistory(int AccountNum) throws RemoteException ;
          public ExchangeRates ViewExchangeRates() throws RemoteException, Exception;
          public int DeleteUserAccount(String UserName) throws RemoteException;
         
    
}

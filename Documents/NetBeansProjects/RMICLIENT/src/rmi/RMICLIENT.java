/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bilal
 */
public class RMICLIENT {
   private static ServicesInterface services; // Our remote object
     private static ClientGUI loginGUI;
    private static ClientHomePage Homepage;
    private static Register registerGUI;
    private static ViewExchangeRates viewrates;
    private static ViewTransactionLog viewLog;
    
    
    
    public static void main(String[] args) {
        initLoginGUI();
        initRegisterGUI();
        initHomePage();
        initExchangeRatesGUI();
       // initViewratesGUI();
      //  initViewLogGUI();
        loginGUI.setVisible(true);
        
          // and grab the remote auth object
        try {
            Registry r = LocateRegistry.getRegistry(1099);

            services = (ServicesInterface) r.lookup("x");
           
        } catch (Exception e) {
            System.out.println("Exception " + e.toString());
        }
    }
      private static void initHomePage() {
        Homepage = new ClientHomePage();
        Homepage.setLocationRelativeTo(null); // center the screen
    }
//      
//      private static void initViewLogGUI(){
//       try {
//           viewLog= new ViewTransactionLog();
//           viewLog.setLocationRelativeTo(null);
//           Transactions x= services.ViewTransactionsHistory(120); //think of a way to get the Same Account number "Shared prefrences"
//           
//           viewLog.setTxtArea(
//                          
//                   "d");
//           
//       } catch (RemoteException ex) {
//          // Logger.getLogger(RMICLIENT.class.getName()).log(Level.SEVERE, null, ex);
//       }
//      }
      
          private static void initExchangeRatesGUI() {
        viewrates = new ViewExchangeRates();
        viewrates.setLocationRelativeTo(null); // center the screen
       
        Homepage.getExchnageRateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ExchangeRates rates= services.ViewExchangeRates();
                    viewrates.setUSDEGP(rates.getUSDEGP());
                     viewrates.setUSDEUR(rates.getUSDEUR());
                     viewrates.setUSDGBP(rates.getUSDGBP());
                     viewrates.setUSDQAR(rates.getUSDQAR());
                     viewrates.setUSDSAR(rates.getUSDSAR());        
                } catch (Exception ex) {
                    Logger.getLogger(RMICLIENT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
          viewrates.getback().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewrates.dispose();
                    initHomePage();
                   
                    Homepage.setVisible(true);
            }
        });
          
          }
        
      
    private static void initLoginGUI(){
        
     loginGUI = new ClientGUI();
        loginGUI.setLocationRelativeTo(null); // center the screen
        // Register the action listeners
        loginGUI.getLoginButton().addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
               try {
                //   if(loginGUI.Getclient().isSelected()){
                String username = loginGUI.getUserName();
                String password = loginGUI.getPassword();
                BankClients client = new  BankClients();
                services.Loign(client, username, password);
                if (client == null) {
                    JOptionPane.showMessageDialog(null, "Wrong username/password. Please try again");
                } else {
                    JOptionPane.showMessageDialog(null, "Logged in successfully!");
                    loginGUI.dispose();
                    initLoginGUI();
                    Homepage.setUsername(client.getUserName());
                    Homepage.setAccnum(client.getAccountNumber());
                    Homepage.setVisible(true);
                }
                   //}
                 //  else if(loginGUI.GetAdmin().isSelected()){//Admin Login
                  // }
                   
               
               } catch (Exception ex) {
                System.out.println("exception " + ex.toString());
            }
         }
     } );
       
  loginGUI.getRegisterButton().addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
                   
             loginGUI.dispose();
             initLoginGUI();
             registerGUI.setVisible(true);
         }
     });
    }
    
    private static void initRegisterGUI() {
        registerGUI = new Register();
        registerGUI.setLocationRelativeTo(null); //center the screen
        // Register the action listeners
        registerGUI.RegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                String username = registerGUI.getUsername();
                String password = registerGUI.getPassword();
                String Fname = registerGUI.getFname();
                String Lname=registerGUI.getLname();
                String ssn=registerGUI.Getssn();
                String email=registerGUI.getEmail();
                
                int newUser = services.Register(username, Fname, Lname,email,password,ssn);

                if (newUser == 0) {
                    JOptionPane.showMessageDialog(null, "Username already exists, please try another one");
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Registed successfully! ");
                    registerGUI.dispose();
                    initRegisterGUI();
                    loginGUI.setVisible(true);
                }

            } catch (Exception ex) {
                   
            }
            }
        });
         registerGUI.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    registerGUI.dispose();
                   initRegisterGUI();
                    loginGUI.setVisible(true);
            }
        });
                 
    }
}

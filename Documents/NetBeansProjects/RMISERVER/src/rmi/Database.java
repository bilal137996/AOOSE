/** **********************************************
 * Advanced Object Oriented Software Engineering *
 * Project - Final Phase                         *
 * Group 12                                      *
 *********************************************** */

/*
* use next() function only once in the hasnext() while loop okay okay
* Shout out for Salma Raouf myself sarah and mr hamankas
*/
package rmi;

import com.mongodb.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import com.mongodb.MongoClient; 

public class Database {

         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );  
         DB database = mongoClient.getDB("admin");

       BasicDBObject Client = new BasicDBObject("Username","johnyx").append("First name","john").append("Last name","locks").append("EMAIL","first@first.com").append("Password","123").append("Balance","50000").append("AccountNumber","5643658");    // make a new record, provide the entity title then the value, and use append to add more entities
       BasicDBObject Client2 = new BasicDBObject("Username","johnson_Y").append("First name","johns").append("Last name","apaller").append("EMAIL","second@second.com").append("Password","123").append("Balance","511230").append("AccountNumber","7192158");    // make a new record, provide the entity title then the value, and use append to add more entities
       BasicDBObject Client3 = new BasicDBObject("Username","antony").append("First name","tony").append("Last name","jim").append("EMAIL","third@third.com").append("Password","123").append("Balance","9728712").append("AccountNumber","0865425");    // make a new record, provide the entity title then the value, and use append to add more entities
       BasicDBObject Client4 = new BasicDBObject("Username","tom").append("First name","tomy").append("Last name","stefson").append("EMAIL","fourth@fourth.com").append("Password","123").append("Balance","100000").append("AccountNumber","645764");    // make a new record, provide the entity title then the value, and use append to add more entities
       BasicDBObject Client5 = new BasicDBObject("Username","jerry").append("First name","jojo").append("Last name","kinder").append("EMAIL","fifth@fifth.com").append("Password","123").append("Balance","40000").append("AccountNumber","326754");    // make a new record, provide the entity title then the value, and use append to add more entities


        BasicDBObject Staf = new BasicDBObject("Username","ahmed").append("First name","ahmed").append("Last name","mido").append("EMAIL","first@first.com").append("Password","123");// make a new record, provide the entity title then the value, and use append to add more entities
       BasicDBObject Staff2 = new BasicDBObject("Username","mohamed").append("First name","mohamed").append("Last name","7amada").append("EMAIL","second@second.com").append("Password","123");    // make a new record, provide the entity title then the value, and use append to add more entities
       BasicDBObject Staff3 = new BasicDBObject("Username","gamal").append("First name","gamal").append("Last name","akram").append("EMAIL","third@third.com").append("Password","123");    // make a new record, provide the entity title then the value, and use append to add more entities
       BasicDBObject Staff4 = new BasicDBObject("Username","mahmoud").append("First name","mahmoud").append("Last name","john").append("EMAIL","fourth@fourth.com").append("Password","123");    // make a new record, provide the entity title then the value, and use append to add more entities
       BasicDBObject Staff5 = new BasicDBObject("Username","osama").append("First name","osama").append("Last name","cena").append("EMAIL","fifth@fifth.com").append("Password","123");// make a new record, provide the entity title then the value, and use append to add more entities


    public static final Collection BankClientss = database.getCollection("Bank_Clients");
    public static final Collection Stafff = database.getCollection("Staff");
    public static final Collection transactions = database.getCollection("Transactions");
    public static final Collection complains = database.getCollection("Complains");
   // connects to the mongodb provided the host name and port number
      
      // use the commented part to authenticate, i don't know if this is the verification part in the marking criteria
      /*
      Authentication (Optional)
MongoDB can be run in a secure mode where access to databases is controlled via authentication. When run in this mode, any client application must provide a list of credentials which will be used to authenticate against. In the Java driver, you simply provide the credentials when creating a MongoClient instance:

MongoCredential credential = MongoCredential.createCredential(userName, database, password);
MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));*/
   // to connect to the database, provide its name
       
      // DBCollection collection = database.getCollection("SE");      // to connect to the collection in the database, provide its name
       // String UserName, String Fname, String Lname, String mail, String pass, String SSN
      
        Collection.insert(Client);  // to add them into the database
        Collection.insert(Client2); 
        Collection.insert(Client3); 
        Collection.insert(Client4); 
        Collection.insert(Client5); 
        /*
        DBObject myDoc = collection.findOne();  // this returns the first made record
        System.out.println(myDoc);  // prints it in json format
        System.out.println(collection.getCount());      // gets how many records are in the database
        //collection.remove(myDoc);     // deletes the record from the database
        */

    


    public Database() {
    }

    public static void insertRecord(Object param, String collectionName) throws RemoteException {
        if (collectionName.equals("Bank_Clients")) {
             BankClients Client = (BankClients) param;
            DBObject client = new BasicDBObject("Username", Client.getUserName()).append("Fname",Client.getFirstName()).append("Lname",Client.getLastName())
                    .append("Password", Client.getPassword()).append("mail",Client.getEmail())//String UserName,String Fname,String Lname,String mail,String pass, String SSN
                    .append("SSN", Client.getSSN());
            BankClientss.insert(client);
        } else if (collectionName.equals("Transactions")) {
            Transactions trans = (Transactions) param;
            DBObject transss = new BasicDBObject("TransactionsNumber", trans.getTransactionNumber())
                    .append("SenderAcc", trans.getSenderAcc())
                    .append("RecepiantAcc", trans.getRecipientAcc())
                    .append("Amount", trans.getAmount())
                    .append("Type", trans.getType()).append("TimeStamp", trans.getTimeStamp());
            transactions.insert(transss);
        } else if (collectionName.equals("Complains")) {
            Complains c = (Complains) param;
            DBObject complain;
          DBObject complai = new BasicDBObject("ID", c.getID())
                    .append("Message", c.getMessage())
                    
                    .append("Type", c.getType()).append("TimeStamp", c.getTimeStamp());
            transactions.insert(complai);
        }
    }

    public static void deleteRecord(String collectionName, String Search) throws RemoteException {
        BasicDBObject searchQuery = new BasicDBObject();
        if (collectionName.equals("Bank_Clients")) {
            searchQuery.put("Username", Search);
            BankClients.remove(searchQuery);
        } else if (collectionName.equals("Staff")) {
            searchQuery.put("Username", Search);
            Staff.remove(searchQuery);
        } else if (collectionName.equals("Complains")) {
            searchQuery.put("ComplainID", Integer.parseInt(Search));
            Complains.remove(searchQuery);
        } 
    }
}
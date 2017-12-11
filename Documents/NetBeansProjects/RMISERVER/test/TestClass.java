/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import rmi.Services;
import rmi.DB;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import junit.framework.*;
import org.bson.Document;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rmi.BankClients;
import rmi.Transactions;
import sun.security.util.Password;

/**
 *
 * @author Bassem Sabry
 */



public class TestClass extends TestCase {

    @Override
    public void setUp() {

    }
public void TestRegister() {
        DB db = new DB();
        Gson gson = new Gson();
        MongoDatabase database = db.getDatabase();
        MongoCollection<Document> collection = database.getCollection("bankClient");

        String username = "client";
        String Password = "client";

        Document doc = collection.find(Filters.eq("Username", username)).first();
        BankClients bankClient = gson.fromJson(doc.toJson(), BankClients.class);
        boolean actual = bankClient.getPassword().equals(Password);
        boolean expected = true;
        Assert.assertTrue(expected == actual);
    }
    public void TestLoign() {
        DB db = new DB();
        Gson gson = new Gson();
        MongoDatabase database = db.getDatabase();
        MongoCollection<Document> collection = database.getCollection("bank_Client");

        String username = "client";
        String Password = "client";

        Document doc = collection.find(Filters.eq("Username", username)).first();
        BankClients bankClient = gson.fromJson(doc.toJson(), BankClients.class);
        boolean actual = bankClient.getPassword().equals(Password);
        boolean expected = true;
        Assert.assertTrue(expected == actual);
    }
    
    public void TestViewTransactionsHistory() {
        
        ArrayList<Transactions> B = new ArrayList<>();
        DB db = new DB();
        Gson gson = new Gson();
        MongoDatabase database = db.getDatabase();
        MongoCollection<Document> collection = database.getCollection("Transactions");

        ArrayList<Document> docs = collection.find().into(new ArrayList<Document>());
        for (Document doc : docs) {
            B.add(gson.fromJson(doc.toJson(), Transactions.class));
        }

        int actual = B.size();
        int expected = 1;

        Assert.assertTrue(expected == actual);
    }
}

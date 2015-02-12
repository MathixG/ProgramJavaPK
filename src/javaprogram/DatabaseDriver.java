/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprogram;

/**
 *
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javaprogram.DatabaseDriver;
import javaprogram.ProgramUI;
import model.Produkt;
import model.categories;



public class DatabaseDriver {
    
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:database.sqlite";
    public Vector<categories> catData;
    private Connection conn;
    public Statement stat;
    private String brak;
    public DatabaseDriver() throws SQLException{
        
        try {
            Class.forName(DatabaseDriver.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
            System.out.println("DZIALA!");
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
        Vector catData = new Vector<categories>();
        this.createTables();
        this.createTablesCategories();
        
    }
    
    public boolean createTables()  {
        String createProduct = "CREATE TABLE IF NOT EXISTS product (productID INTEGER PRIMARY KEY AUTOINCREMENT, productName varchar(255), productPrice varchar(255), purchasePlace varchar(255),  purchaseDate varchar(255), expiryDate varchar(255), productWarranty varchar(255), categoryID INTEGER)";
        try {
            stat.execute(createProduct);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
     public boolean createTablesCategories() throws SQLException  {
        String createCategory = "CREATE TABLE IF NOT EXISTS categories (categoryID INTEGER PRIMARY KEY AUTOINCREMENT, categoryName varchar(255) UNIQUE)";
        try {
            stat.execute(createCategory);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        ResultSet result = this.stat.executeQuery("SELECT * FROM categories WHERE categoryName = 'BRAK'");
        
        while(result.next())
        {
            brak = result.getString("categoryName");
            //System.out.println(brak + "<------- tt");
        }
        if(brak == null)
        {
           this.insertCategory("BRAK");
        } 
        
        
       
        return true;
    }
     
     public boolean insertCategory(String categoryName) throws SQLException
     {
         PreparedStatement prepStmt = conn.prepareStatement("insert into categories values (NULL, ?)");
         prepStmt.setString(1, categoryName);
         prepStmt.execute();
        return true;
     }
    
    public boolean insertProduct(String productName, String productPrice, String purchasePlace, String purchaseDate, String expiryDate, String productWarranty, int categoryID) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into product values (NULL, ?, ?, ?, ?, ? , ?, ?);");
            prepStmt.setString(1, productName);
            prepStmt.setString(2, productPrice);
            prepStmt.setString(3, purchasePlace);
            prepStmt.setString(4, purchaseDate);
            prepStmt.setString(5, expiryDate);
            prepStmt.setString(6, productWarranty);
            prepStmt.setInt(7, categoryID);
            prepStmt.execute();
            
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu czytelnika");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Vector<Produkt> selectALLProducts() throws SQLException{
        
        ResultSet result = stat.executeQuery("SELECT * FROM product");
        
        Vector<Produkt> data;
        data = new Vector<Produkt>();
        Produkt tempprodukt;
            while(result.next()) 
            {
               
             tempprodukt = new Produkt();
             tempprodukt.id = result.getInt("productID");
             tempprodukt.productName = result.getString("productName");
             tempprodukt.productPrice = result.getString("productPrice");
             tempprodukt.purchasePlace = result.getString("purchasePlace");
             tempprodukt.purchaseDate = result.getString("purchaseDate");
             tempprodukt.expiryDate = result.getString("expiryDate");
             tempprodukt.productWarranty = result.getString("productWarranty");
             tempprodukt.categoryID = result.getInt("categoryID");
             data.addElement(tempprodukt);
                
               
                
            }
        return data;
    }
    
    public Vector<categories> SelectALLCategories() throws SQLException{
        ResultSet result = stat.executeQuery("SELECT * FROM CATEGORIES");
        int id,i;
        i = 0;
        String name;
        Vector<categories> data;
        data = new Vector<categories>();
        categories cattempdata;
        
        while(result.next())
        {
        cattempdata = new categories();
       cattempdata.ID = result.getInt("categoryID");
       cattempdata.CN = result.getString("categoryName");
        data.addElement(cattempdata);
        //System.out.println(data.get(i).ID + " " + data.get(i).CN + " Data*** ");
        i++;
        }
       //System.out.println(data.get(4).ID + " " + data.get(4).CN + " Data*** ");
      //System.out.println(data.get(5).ID + " " + data.get(5).CN + " Data*** ");
        //System.out.println(data.get(i).ID + " " + data.get(i).CN + " Data***8 ");
       return data;
       
    }
    
    public void deleteProducts() throws SQLException {
        String delete = "DELETE FROM product";
        stat.execute(delete);
    }
    
    
    
    
    
    
    
}

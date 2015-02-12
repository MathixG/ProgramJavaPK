/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 
 */
public class Produkt {
    
    public int id;
    public String productName;
    public int categoryID;
    public String productPrice;
    public String purchasePlace;
    public String purchaseDate;
    public String expiryDate;
    public String productWarranty;
    
  
    
    public Produkt() {}
    public Produkt(int id, String productName, int categoryID, String productPrice, String purchasePlace, String purchaseDate, String expiryDate, String productWarranty){
        this.id = id;
        this.productName = productName;
        this.categoryID = categoryID;
        this.productPrice = productPrice;
        this.purchasePlace = purchasePlace;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.productWarranty = productWarranty;
        
    }
            
    
}

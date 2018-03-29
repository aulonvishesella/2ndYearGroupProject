/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Accounts;

/**
 *
 * @author Ramee
 */
public class Customer {
    
    
    private int customerID;
    private String customerName;
    private String customerStatus;
    
    public Customer(int customerID,String customerName, String customerStatus){
    
    this.customerID = customerID;
    this.customerName = customerName;
    this.customerStatus = customerStatus;
    
    }
    
    public int getCustomerID(){
    
    return customerID;
    }
    
    public String getCustomerName(){
    
    return customerName;
    }
    
    public String getCustomerStatus(){
    
    return customerStatus;    
    }
    
}

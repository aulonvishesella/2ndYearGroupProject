/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Database;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import teamproject.Accounts.Customer;
import teamproject.Tasks.Task;



/**
 *
 * @author Ramee
 */
public class Jdbc {
    
   
     Connection conn = getConnection();
    
    public Jdbc() throws Exception{
    
    getConnection();
    
    }
    
    
    public static Connection getConnection() throws Exception{
    
     try{
             String driver = "com.mysql.jdbc.Driver";
             String url = "jdbc:mysql://localhost:3306/bapers";
             String username = "root";
             String password = "aulon123";
             
             Class.forName(driver);
             
             Connection conn = DriverManager.getConnection(url, username, password);
             
             
             
             System.out.println("connected");
             
             
             return conn;
    } catch(Exception e){System.out.println(e);}
   
    return null;
    
    }
    
    
    public boolean validate_login(String username,String password) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
          String sql=("SELECT * FROM staff WHERE UserID = ? AND Password = ?");
          
          pst = conn.prepareStatement(sql);
          
          pst.setString(1, username);
          pst.setString(2, password);
          
          rs = pst.executeQuery();
         
          if(rs.next())
              return true;
          else 
              return false;
          
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        
    }
    //retrive customer data
    public ArrayList<Customer> customerList(){
        
    ArrayList<Customer> customerList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from customers
            String getData = "SELECT * FROM customer";
            
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(getData);
            
            Customer customer;
            
            while(rs.next()){
                customer = new Customer(rs.getInt("CustomerID"),rs.getString("FirstName"),rs.getString("CustStatus"));
                customerList.add(customer);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerList;
    } 
    
    public ArrayList<Task> taskList(){
         ArrayList<Task> taskList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from tasks
            String getData = "SELECT * FROM task";
            
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(getData);
            
            Task task;
            
            while(rs.next()){
                task = new Task(rs.getInt("TaskID"),rs.getString("TaskDescription"),rs.getString("Location"),rs.getString("ShelfSlot"),rs.getFloat("Price"),rs.getInt("Duration"));
                taskList.add(task);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taskList;
    }
    
    //populates customer table with filter
    public void displayCustomer(JTable customerTable,String status){
        
        String customerStatus= status;
        
        ArrayList <Customer> list = customerList();
        
        DefaultTableModel model =(DefaultTableModel) customerTable.getModel();
        
        Object[] row = new Object[3];
        
         for(int i = 0;i<list.size();i++){
        
             if(list.get(i).getCustomerStatus().contains(status)){
                 
             row[0]=list.get(i).getCustomerID();
             row[1]=list.get(i).getCustomerName();
             row[2]=list.get(i).getCustomerStatus();
             model.addRow(row);
             
             
             } 
             
         }
       
    }
    
    public void displayTasks(JTable taskTable){
        System.out.println("Display Task");
        ArrayList <Task> list = taskList();
        
        DefaultTableModel model =(DefaultTableModel) taskTable.getModel();
        
        Object[] row = new Object[6];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getTaskID();
             row[1]=list.get(i).getTaskDescription();
             row[2]=list.get(i).getLocation();
             row[3]= list.get(i).getShelfSlot();
             row[4]= list.get(i).getPrice();
             row[5] = list.get(i).getDuration();
             model.addRow(row);
             
         }
       
    }    
   
    
     public void displayCustomer(JTable customerTable){
      
        System.out.println("Display Customer");
        ArrayList <Customer> list = customerList();
        
        DefaultTableModel model =(DefaultTableModel) customerTable.getModel();
        
        Object[] row = new Object[3];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getCustomerID();
             row[1]=list.get(i).getCustomerName();
             row[2]=list.get(i).getCustomerStatus();
             model.addRow(row);
             
         }
       
    }
    
    public boolean setStatus(int id,String status) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE customer SET CustStatus = ? WHERE CustomerID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, status);
          pst.setInt(2, id);
          
          System.out.println("update to " +id + status);
          
          pst.executeUpdate();
          
          pst.close();
          
       
          
         
          
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false;
    }
     
     
    
}

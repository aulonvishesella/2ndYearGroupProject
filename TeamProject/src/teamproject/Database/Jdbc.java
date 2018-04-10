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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import teamproject.Accounts.Customer;
import teamproject.Tasks.Task;
import teamproject.Jobs.Job;
import teamproject.Payment.PaymentRecord;
import teamproject.Admin.StaffAccount;
import teamproject.Bapers;





/**
 *
 * @author Ramee
 */
public class Jdbc {
    
   Bapers bapers;
     Connection conn = getConnection();
    
    public Jdbc() throws Exception{
    
    getConnection();
    
    
    
    
    }
    
    
    public static Connection getConnection() throws Exception{
    
     try{
             String driver = "com.mysql.jdbc.Driver";
             String url = "jdbc:mysql://localhost:3306/bapersv2";
             String username = "root";
             String password = "1234";
             
             Class.forName(driver);
             
             Connection conn = DriverManager.getConnection(url, username, password);
             
             
             
             System.out.println("connected");
             
             
             return conn;
    } catch(Exception e){System.out.println(e);}
   
    return null;
    
    }
    
    public String retrieveRole(String username) throws SQLException{
    
    
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = ("SELECT StaffRole From staff where UserID = ?");   
    
    pst = conn.prepareStatement(sql);
    pst.setString(1, username);
    
    rs = pst.executeQuery();
        if(rs.next())
              return rs.getString(1);
          else 
              return errorMsg;
          
         
          
      }catch(Exception E){
          E.printStackTrace();
          return errorMsg;
      }
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
    
    
    public ArrayList<Job> jobList(){
        ArrayList<Job> jobList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from tasks
            String getData = "SELECT * FROM job";
            
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(getData);
            
            Job job;
            
            while(rs.next()){
                job = new Job(rs.getInt("JobNo"),rs.getString("JobCode"),rs.getString("JobDescription"),rs.getDate("JobDate"),rs.getInt("Customer_CustomerID"));
                jobList.add(job);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobList;
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
    
    
       public ArrayList<PaymentRecord> paymentList(){
         ArrayList<PaymentRecord> paymentList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from tasks
            String getData = "SELECT * FROM payment";
            
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(getData);
            
            PaymentRecord paymentRecord;
            
            while(rs.next()){
                paymentRecord = new PaymentRecord(rs.getInt("PaymentID"),rs.getString("PaymentType"),rs.getFloat("PaymentAmount"),rs.getInt("Job_JobNo"));
                paymentList.add(paymentRecord);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paymentList;
    }
    
    public ArrayList<StaffAccount> staffList(){
         ArrayList<StaffAccount> staffList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from tasks
            String getData = "SELECT * FROM staff";
            
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(getData);
            
            StaffAccount staffAccount;
            
            while(rs.next()){
                staffAccount = new StaffAccount(rs.getInt("StaffID"),rs.getString("StaffRole"),rs.getString("StaffFirstName"),rs.getString("StaffLastName"),rs.getString("UserID"),rs.getString("Password"));
                staffList.add(staffAccount);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staffList;
    }
       
       
       
       
        public void displayPayment(JTable paymentTable){
              System.out.println("Display PaymentRecords");
        ArrayList <PaymentRecord> list = paymentList();
        
        DefaultTableModel model =(DefaultTableModel) paymentTable.getModel();
        
        Object[] row = new Object[4];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getPaymentID();
             row[1]=list.get(i).getPaymentType();
             row[2]=list.get(i).getPaymentAmount();
             row[3]= list.get(i).getJobNo();
             
           
             model.addRow(row);
             
         }
    }
    
     public void displayStaff(JTable staffTable){
              System.out.println("Display StaffAccounts");
        ArrayList <StaffAccount> list = staffList();
        
        DefaultTableModel model =(DefaultTableModel) staffTable.getModel();
        
        Object[] row = new Object[6];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getStaffID();
             row[1]=list.get(i).getStaffRole();
             row[2]=list.get(i).getStaffFirstName();
             row[3]=list.get(i).getStaffLastName();
             row[4]=list.get(i).getUserID();
             row[5]=list.get(i).getPassword();
             
             
             
           
             model.addRow(row);
             
         }
    }
       
        public void displayStaff(JTable staffTable,String StaffRole){
            String staffRole= StaffRole;
        
        ArrayList <StaffAccount> list = staffList();
        
        DefaultTableModel model =(DefaultTableModel) staffTable.getModel();
        
        Object[] row = new Object[6];
        
         for(int i = 0;i<list.size();i++){
        
             if(list.get(i).getStaffRole().contains(StaffRole)){
                 
            row[0]=list.get(i).getStaffID();
             row[1]=list.get(i).getStaffRole();
             row[2]=list.get(i).getStaffFirstName();
             row[3]=list.get(i).getStaffLastName();
             row[4]=list.get(i).getUserID();
             row[5]=list.get(i).getPassword();
                model.addRow(row);
             
             } 
             
         }
    }
   public void displayStaff(JTable staffTable,int staffID){
        
        int StaffID= staffID;
        
        ArrayList <StaffAccount> list = staffList();
        
        DefaultTableModel model =(DefaultTableModel) staffTable.getModel();
        
          Object[] row = new Object[6];
        
        
         for(int i = 0;i<list.size();i++){
        
             if(list.get(i).getStaffID() == staffID){
                 
             row[0]=list.get(i).getStaffID();
             row[1]=list.get(i).getStaffRole();
             row[2]=list.get(i).getStaffFirstName();
             row[3]=list.get(i).getStaffLastName();
             row[4]=list.get(i).getUserID();
             row[5]=list.get(i).getPassword();
             model.addRow(row);
             
             
             } 
             
         }
       
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
        public void displayCustomer(JTable customerTable,int customerID){
        
        int CustomerID = customerID;
        
        ArrayList <Customer> list = customerList();
        
        DefaultTableModel model =(DefaultTableModel) customerTable.getModel();
        
        Object[] row = new Object[3];
        
         for(int i = 0;i<list.size();i++){
        
             if(list.get(i).getCustomerID() == customerID){
                 
             row[0]=list.get(i).getCustomerID();
             row[1]=list.get(i).getCustomerName();
             row[2]=list.get(i).getCustomerStatus();
             model.addRow(row);
             
             
             } 
             
         }
       
    }
    
    
    
    
     public void displayJob(JTable jobTable,int jobNo){
        
       int jobNumber= jobNo;
        
        ArrayList <Job> list = jobList();
        
        DefaultTableModel model =(DefaultTableModel) jobTable.getModel();
        
        Object[] row = new Object[5];
        
         for(int i = 0;i<list.size();i++){
        
             if(list.get(i).getJobNo() == jobNo){
                 
             
             
             
             row[0]=list.get(i).getJobNo();
             row[1]=list.get(i).getJobCode();
             row[2]=list.get(i).getJobDescription();
             row[3]= list.get(i).getJobDate();
             row[4]= list.get(i).getCustomerID();
              model.addRow(row);
             
             } 
             
         }
       
    }
    
     
    
    
    public void displayJobs(JTable jobTable){
              System.out.println("Display Job");
        ArrayList <Job> list = jobList();
        
        DefaultTableModel model =(DefaultTableModel) jobTable.getModel();
        
        Object[] row = new Object[5];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getJobNo();
             row[1]=list.get(i).getJobCode();
             row[2]=list.get(i).getJobDescription();
             row[3]= list.get(i).getJobDate();
             row[4]= list.get(i).getCustomerID();
           
             model.addRow(row);
             
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
    
    
    public void displayTasks(JTable taskTable,int taskID){
        
        int TaskID= taskID;
        
        ArrayList <Task> list = taskList();
        
        DefaultTableModel model =(DefaultTableModel) taskTable.getModel();
        
          Object[] row = new Object[6];
        
        
         for(int i = 0;i<list.size();i++){
        
             if(list.get(i).getTaskID()== taskID){
                 
             row[0]=list.get(i).getTaskID();
             row[1]=list.get(i).getTaskDescription();
             row[2]=list.get(i).getLocation();
             row[3]= list.get(i).getShelfSlot();
             row[4]= list.get(i).getPrice();
             row[5] = list.get(i).getDuration();
             model.addRow(row);
             
             
             } 
             
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
      public boolean setRole(int id,String staffRole) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE staff SET StaffRole = ? WHERE StaffID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, staffRole);
          pst.setInt(2, id);
          
          System.out.println("update to " +id + staffRole);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
     
    
       public boolean setStaffFirstName(int id,String staffFirstName) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE staff SET StaffFirstName = ? WHERE StaffID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, staffFirstName);
          pst.setInt(2, id);
          
          System.out.println("update to " +id + staffFirstName);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
       public boolean setStaffLastName(int id,String staffLastName) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE staff SET StaffLastName = ? WHERE StaffID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, staffLastName);
          pst.setInt(2, id);
          
          System.out.println("update to " +id + staffLastName);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
      
       
        public boolean setUserID(int id,String userID) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE staff SET UserID = ? WHERE StaffID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, userID);
          pst.setInt(2, id);
          
          System.out.println("update to " +id + userID);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
      
    public boolean setPassword(int id,String password) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE staff SET Password = ? WHERE StaffID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, password);
          pst.setInt(2, id);
          
          System.out.println("update to " +id + password);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
       
       
       
       
        public boolean setDeletion(int id) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("delete from staff WHERE StaffID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          
          pst.setInt(1, id);
          
          System.out.println("update to " +id);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
      
        
        
        public boolean setDeletionCustomer(int id) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("delete from Customer WHERE CustomerID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          
          pst.setInt(1, id);
          
          System.out.println("update to " +id);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
     public boolean setDeletionTask(int id) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("delete from task WHERE TaskID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          
          pst.setInt(1, id);
          
          System.out.println("update to " +id);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    } 
        
        
        
        
        
      
       public boolean setNewAccount(String staffRole,String staffFirstName,String staffLastName, String UserID,String password) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("insert into staff (StaffRole,StaffFirstName,StaffLastName,UserID,Password) values (?,?,?,?,?)");
          
          pst = conn.prepareStatement(update);
          
         
          
          pst.setString(1, staffRole);
          pst.setString(2, staffFirstName);
          pst.setString(3, staffLastName);
          pst.setString(4, UserID);
          pst.setString(5,password);
          System.out.println("added new account ");
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
      
        public boolean setNewCustomerAccount(String AccNumber,String forename,String surename,String firstLineAddress,String postcode, String phoneNumber,String discount,String custStatus) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("insert into customer (AccountNo,FirstName,Surname,1stLineAddress,Postcode,PhoneNo,Discount,CustStatus) values (?,?,?,?,?,?,?,?)");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, AccNumber);
          pst.setString(2, forename);
          pst.setString(3, surename);
          pst.setString(4, firstLineAddress);
          pst.setString(5, postcode);
          pst.setString(6, phoneNumber);
          pst.setString(7,discount);
          pst.setString(8, custStatus);
          System.out.println("added new account ");
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
        public boolean createTask(String taskDescription,String location,String shelfSlot, float price, int duration,int variableID) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("insert into task (TaskDescription,Location,ShelfSlot,Price,Duration, Variable_VariableID) values (?,?,?,?,?,?)");
          
          pst = conn.prepareStatement(update);
          
         
          
          
          pst.setString(1, taskDescription);
          pst.setString(2, location);
          pst.setString(3, shelfSlot);
          pst.setFloat(4, price);
          pst.setInt(5,duration);
          pst.setInt(6, variableID);
          
          System.out.println("added new account ");
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
        
      public boolean setTaskDescription(int id,String taskDescr) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE task SET TaskDescription = ? WHERE TaskID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, taskDescr);
          pst.setInt(2,id);
          
          System.out.println("update to " +id + taskDescr);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
        
      
      public boolean setTaskLocation(int id,String taskLoc) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE task SET Location = ? WHERE TaskID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, taskLoc);
          pst.setInt(2,id);
          
          System.out.println("update to " +id + taskLoc);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
        
       public boolean setTaskShelfSlot(int id,String shelfSlot) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE Task SET ShelfSlot = ? WHERE TaskID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, shelfSlot);
          pst.setInt(2,id);
          
          System.out.println("update to " +id + shelfSlot);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
      
       
        public boolean setTaskPrice(int id,float price) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE Task SET Price = ? WHERE TaskID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setFloat(1, price);
          pst.setInt(2,id);
          
          System.out.println("update to " +id + price);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
       
       
       public boolean setTaskDuration(int id,int duration) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE Task SET Duration = ? WHERE TaskID = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setInt(1, duration);
          pst.setInt(2,id);
          
          System.out.println("update to " +id + duration);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
       
       
       
      
     public boolean setJobCode(int id,int jobCode) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
       
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE job SET JobCode = ? WHERE JobNo = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setInt(1, jobCode);
          pst.setInt(2,id);
          
      System.out.println("update to " +id + jobCode);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
    
    /**
     *
     * @param id
     * @param jobDescription
     * @return
     * @throws Exception
     */
    public boolean setJobDescription(int id,String jobDescription) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
       
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE job SET JobDescription = ? WHERE JobNo = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, jobDescription);
          pst.setInt(2,id);
          
      System.out.println("update to " +id + jobDescription);
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
    public boolean setJobDate(int id,String jobDate) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
       
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE job SET JobDate = ? WHERE JobNo = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, jobDate);
          pst.setInt(2,id);
          
      System.out.println("update to " +id + jobDate);
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
     public boolean setCustomerID(int id,int customerID) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
       
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE job SET Customer_CustomerID = ? WHERE JobNo = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setInt(1, customerID);
          pst.setInt(2,id);
          
      System.out.println("update to " +id + customerID);
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
    public boolean setDeletionJob(int id) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update =("delete from job WHERE JobNo = ?");
          
          pst = conn.prepareStatement(update);
          
         
          
          pst.setInt(1, id);
          
          System.out.println("update to " +id);
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    } 
        
       public boolean searchDescription(int id) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
        
      try{
         // Connection conn = getConnection();
         
          String update = "select * from job WHERE JobNo = ?";
          
          pst = conn.prepareStatement(update);
          
         
          
          pst.setInt(1, id);
          rs=pst.executeQuery();
          if(rs.next()){
              
              String data2=rs.getString("JobDescription");
            
          }
         
          
        
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    } 
          
        
     
     
}

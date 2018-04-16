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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import teamproject.Accounts.Customer;
import teamproject.Tasks.Task;
import teamproject.Jobs.Job;
import teamproject.Payment.PaymentRecord;
import teamproject.Admin.StaffAccount;
import teamproject.Bapers;
import teamproject.Reports.IndividualJobReport;
import teamproject.Tasks.JobTasks;
import teamproject.Reports.IndividualPerformanceReport;
import teamproject.Reports.SummaryReport;
import teamproject.Reports.TotalSummary;





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
             String url = "jdbc:mysql://localhost:3306/test";
             String username = "root";
             String password = "aulon123";
             
             Class.forName(driver);
             
             Connection conn = DriverManager.getConnection(url, username, password);
             
             
             
             System.out.println("connected");
             
             
             return conn;
    } catch(Exception e){System.out.println(e);}
   
    return null;
    
    }
    public String retrieveStatusInProgress(int x)throws SQLException{
        Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = ( "SELECT State FROM job_has_task WHERE Job_JobNo = ? AND State!= 'Completed' AND State!='Pending'");   
    
    pst = conn.prepareStatement(sql);
   
    pst.setInt(1, x);
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
    
    public boolean retrieveCustomerIDToDelete(int x)throws SQLException{
        Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = ( "select CustomerID FROM Customer WHERE CustomerID = ?");   
    
    pst = conn.prepareStatement(sql);
   
    pst.setInt(1, x);
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
    public boolean retrieveJobIDFromJob(int x)throws SQLException{
        Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = ( "select JobNo FROM Job WHERE JobNo = ?");   
    
    pst = conn.prepareStatement(sql);
   
    pst.setInt(1, x);
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
     public boolean retrieveTaskIDFromJobHasTask(int x)throws SQLException{
        Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = ( "select Task_TaskID from job_has_task where Task_TaskID = ?");   
    
    pst = conn.prepareStatement(sql);
   
    pst.setInt(1, x);
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
     
       public boolean retrieveTaskIDFromTask(int x)throws SQLException{
        Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = ( "select TaskID from task where TaskID = ?");   
    
    pst = conn.prepareStatement(sql);
   
    pst.setInt(1, x);
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
    
       public boolean retrieveAdmin(int x)throws SQLException{
        Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = ( "select StaffID from staff where StaffID = ?");   
    
    pst = conn.prepareStatement(sql);
   
    pst.setInt(1, x);
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
    
 public String retrieveStatusPending(int x) throws SQLException{
        Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = ( "SELECT State From job_has_task Where Job_JobNo = ? AND State!='In-Progress' AND State!= 'Completed'");   
    
    pst = conn.prepareStatement(sql);
   pst.setInt(1, x);
    
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

    
    
    
    public String retrieveStatusComplete(int x) throws SQLException{
        Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = ("select State FROM job_has_task WHERE Job_JobNo= ? AND State != 'Pending' AND State!= 'In-Progress'");   
  
    pst = conn.prepareStatement(sql);
    pst.setInt(1, x); 
    
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
    
    
    
    
    
    public boolean retrieveStaff(String staffname) throws SQLException{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = "select jt.CompletedBy as 'Staff', j.JobCode as 'Job Code', t.TaskID, t.Location as 'Department', DATE(j.JobDate) as 'Date', jt.StartTime as 'Start Time', jt.TimeTaken as 'Time Taken', sum(jt.TimeTaken)\n" +
"from staff s\n "  +
"inner join staff_has_task st on s.StaffID = st.Staff_StaffID\n" +
"inner join task t on st.Task_TaskId = t.TaskID\n" +
"inner join job_has_task jt on t.TaskID = jt.Task_TaskID\n" +
"inner join job j on jt.Job_JobNo = j.JobNo\n" +
"where j.JobDate between '2017-12-23' and '2018-01-10' and jt.CompletedBy = ?\n" +
"group by jt.CompletedBy, JobCode, t.TaskID ";
            
    
    pst = conn.prepareStatement(sql);
    pst.setString(1, staffname);
    
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
      public boolean retrieveAccount(String accountNumber) throws SQLException{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String errorMsg = "N/A";
    try{
    String sql = "select c.AccountNo as 'Account', j.JobCode as 'Job Code', j.JobDescription as 'Job Description', t.Price, jt.Task_TaskID\n" +
"from job j\n" +
"inner join customer c on j.Customer_CustomerID = c.CustomerID\n" +
"inner join job_has_task jt on j.JobNo = jt.Job_JobNo\n" +
"inner join task t on t.TaskID = jt.Task_TaskID\n" +
"where j.JobDate between '2017-12-20' and '2018-01-10' and c.AccountNo=?";
            
    
    pst = conn.prepareStatement(sql);
    pst.setString(1, accountNumber);
    
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
    public ArrayList<SummaryReport> summaryAfternoonList(){
        
    ArrayList<SummaryReport> summaryAfternoonList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from customers
            String getData = "select DATE(j.JobDate), t.Location, sum(jt.TimeTaken), jt.StartTime\n" +
"from job j\n" +
"inner join job_has_task jt on j.JobNo = jt.Job_JobNo\n" +
"inner join task t on t.TaskID = jt.Task_TaskID \n" +
"where j.JobDate between '2017-01-01' and '2018-05-01' #choose date period\n" +
"and jt.StartTime between '13:00' and '17:00' #choose time period\n" +
"group by t.Location, j.JobDate\n" +
"order by j.JobDate, t.Location;";
            
            Statement st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(getData);
            
            SummaryReport sr;
            
            while(rs.next()){
                sr = new SummaryReport(rs.getDate("DATE(j.JobDate)"),rs.getString("t.Location"),rs.getInt("sum(jt.TimeTaken)"),rs.getString("jt.Starttime"));
                  summaryAfternoonList.add(sr);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summaryAfternoonList;
    } 
     public ArrayList<SummaryReport> summaryEveningList(){
        
    ArrayList<SummaryReport> summaryEveningList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from customers
            String getData = "select DATE(j.JobDate), t.Location, sum(jt.TimeTaken), jt.StartTime\n" +
"from job j\n" +
"inner join job_has_task jt on j.JobNo = jt.Job_JobNo\n" +
"inner join task t on t.TaskID = jt.Task_TaskID \n" +
"where j.JobDate between '2017-01-01' and '2018-05-01' #choose date period\n" +
"and jt.StartTime between '17:00' and '22:00' #choose time period\n" +
"group by t.Location, j.JobDate\n" +
"order by j.JobDate, t.Location;";
            
            Statement st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(getData);
            
            SummaryReport sr;
            
            while(rs.next()){
                sr = new SummaryReport(rs.getDate("DATE(j.JobDate)"),rs.getString("t.Location"),rs.getInt("sum(jt.TimeTaken)"),rs.getString("jt.Starttime"));
                  summaryEveningList.add(sr);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summaryEveningList;
    } 
    
    
    
    
    
    public ArrayList<SummaryReport> summaryMorningList(){
        
    ArrayList<SummaryReport> summaryMorningList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from customers
            String getData = "select DATE(j.JobDate), t.Location, sum(jt.TimeTaken), jt.StartTime\n" +
"from job j\n" +
"inner join job_has_task jt on j.JobNo = jt.Job_JobNo\n" +
"inner join task t on t.TaskID = jt.Task_TaskID \n" +
"where j.JobDate between '2017-01-01' and '2018-05-01' #choose date period\n" +
"and jt.StartTime between '05:00' and '13:00' #choose time period\n" +
"group by t.Location, j.JobDate\n" +
"order by j.JobDate, t.Location;";
            
            Statement st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(getData);
            
            SummaryReport sr;
            
            while(rs.next()){
                sr = new SummaryReport(rs.getDate("DATE(j.JobDate)"),rs.getString("t.Location"),rs.getInt("sum(jt.TimeTaken)"),rs.getString("jt.Starttime"));
                  summaryMorningList.add(sr);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summaryMorningList;
    } 
    
      public ArrayList<TotalSummary> summaryTotalAfternoonList(){
        
          
    ArrayList<TotalSummary> summaryTotalAfternoonList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from customers
            String getData = "select t.Location, sum(jt.TimeTaken)\n" +
"from job j\n" +
"inner join job_has_task jt on j.JobNo = jt.Job_JobNo\n" +
"inner join task t on t.TaskID = jt.Task_TaskID \n" +
"where j.JobDate between '2017-01-01' and '2018-05-01' #choose date period\n" +
"and jt.StartTime between '13:00' and '17:00' #choose time period\n" +
"group by t.Location \n" +
"order by t.Location";
            
            Statement st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(getData);
            
            TotalSummary sr;
            
            while(rs.next()){
                sr = new TotalSummary(rs.getString("t.Location"),rs.getInt("sum(jt.TimeTaken)"));
                  summaryTotalAfternoonList.add(sr);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summaryTotalAfternoonList;
    } 
    
    
    
      public ArrayList<TotalSummary> summaryTotalEveningList(){
        
          
    ArrayList<TotalSummary> summaryTotalEveningList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from customers
            String getData = "select t.Location, sum(jt.TimeTaken)\n" +
"from job j\n" +
"inner join job_has_task jt on j.JobNo = jt.Job_JobNo\n" +
"inner join task t on t.TaskID = jt.Task_TaskID \n" +
"where j.JobDate between '2017-01-01' and '2018-05-01' #choose date period\n" +
"and jt.StartTime between '17:00' and '22:00' #choose time period\n" +
"group by t.Location \n" +
"order by t.Location";
            
            Statement st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(getData);
            
            TotalSummary sr;
            
            while(rs.next()){
                sr = new TotalSummary(rs.getString("t.Location"),rs.getInt("sum(jt.TimeTaken)"));
                  summaryTotalEveningList.add(sr);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summaryTotalEveningList;
    } 
    
    
    
    
      public ArrayList<TotalSummary> summaryTotalMorningList(){
        
          
    ArrayList<TotalSummary> summaryTotalMorningList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from customers
            String getData = "select t.Location, sum(jt.TimeTaken)\n" +
"from job j\n" +
"inner join job_has_task jt on j.JobNo = jt.Job_JobNo\n" +
"inner join task t on t.TaskID = jt.Task_TaskID \n" +
"where j.JobDate between '2017-01-01' and '2018-05-01' #choose date period\n" +
"and jt.StartTime between '05:00' and '13:00' #choose time period\n" +
"group by t.Location \n" +
"order by t.Location";
            
            Statement st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(getData);
            
            TotalSummary sr;
            
            while(rs.next()){
                sr = new TotalSummary(rs.getString("t.Location"),rs.getInt("sum(jt.TimeTaken)"));
                  summaryTotalMorningList.add(sr);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summaryTotalMorningList;
    } 
    
    
    public ArrayList<IndividualPerformanceReport> individualPerformanceList(){
        
    ArrayList<IndividualPerformanceReport> individualPerformanceList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from customers
            String getData = "select jt.CompletedBy as 'Staff', j.JobCode as 'Job Code', t.TaskID, t.Location as 'Department', DATE(j.JobDate) as 'Date', jt.StartTime as 'Start Time', jt.TimeTaken as 'Time Taken', sum(jt.TimeTaken)\n" +
"from staff s\n" +
"inner join staff_has_task st on s.StaffID = st.Staff_StaffID\n" +
"inner join task t on st.Task_TaskId = t.TaskID\n" +
"inner join job_has_task jt on t.TaskID = jt.Task_TaskID\n" +
"inner join job j on jt.Job_JobNo = j.JobNo\n" +
"where j.JobDate between '2017-12-23' and '2018-01-10'\n" +
"group by jt.CompletedBy, JobCode, t.TaskID";
            
            Statement st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(getData);
            
            IndividualPerformanceReport ipr;
            
            while(rs.next()){
                ipr = new IndividualPerformanceReport(rs.getString("Staff"),rs.getString("Job Code"),rs.getInt("t.TaskID"),rs.getString("Department"),rs.getDate("Date"),rs.getString("Start Time"),rs.getInt("Time Taken"),rs.getInt("sum(jt.TimeTaken)"));
                individualPerformanceList  .add(ipr);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return individualPerformanceList;
    } 
    
    public ArrayList<IndividualJobReport> individualJobList(){
        
    ArrayList<IndividualJobReport> individualJobList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from customers
            String getData = "select c.AccountNo as 'Account', j.JobCode as 'Job Code', j.JobDescription as 'Job Description', t.Price, jt.Task_TaskID\n" +
"from job j\n" +
"inner join customer c on j.Customer_CustomerID = c.CustomerID\n" +
"inner join job_has_task jt on j.JobNo = jt.Job_JobNo\n" +
"inner join task t on t.TaskID = jt.Task_TaskID\n" +
"where j.JobDate between '2017-12-20' and '2018-01-10'";
            
            Statement st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(getData);
            
            IndividualJobReport ijr;
            
            while(rs.next()){
                ijr = new IndividualJobReport(rs.getString("Account"),rs.getString("Job Code"),rs.getString("Job Description"),rs.getFloat("t.Price"),rs.getInt("jt.Task_TaskID"));
                individualJobList .add(ijr);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return individualJobList;
    } 
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
    
    public ArrayList<JobTasks> jobTask(){
        ArrayList<JobTasks> jobList = new ArrayList<>();
    
        try {
           // Connection conn = getConnection();
            //retrieves data from tasks
            String getData = "SELECT * FROM job_has_task ";
            
            PreparedStatement st = conn.prepareStatement(getData);
            
            ResultSet rs = st.executeQuery(getData);
            
            JobTasks jobTasks;
            
            while(rs.next()){
                jobTasks = new JobTasks(rs.getInt("Task_TaskID"),rs.getString("State"),rs.getInt("Job_JobNo"));
                jobList.add(jobTasks);
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobList;
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
                job = new Job(rs.getInt("JobNo"),rs.getString("JobCode"),rs.getString("JobDescription"),rs.getDate("JobDate"),rs.getInt("Customer_CustomerID"),rs.getString("JobStatus"));
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
    
    public void displayTotalEveningSummary(JTable totalEveningTable){
              System.out.println("Display jobHasTask");
        ArrayList <TotalSummary> list = summaryTotalEveningList();
        
        DefaultTableModel model =(DefaultTableModel) totalEveningTable.getModel();
        
        Object[] row = new Object[2];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getLocation();
             row[1]=list.get(i).getTimeTaken();
         
             
           
             model.addRow(row);
             
         }
    }
    
    
    
    
    
    
    
     public void displayTotalMorningSummary(JTable totalMorningTable){
              System.out.println("Display jobHasTask");
        ArrayList <TotalSummary> list = summaryTotalMorningList();
        
        DefaultTableModel model =(DefaultTableModel) totalMorningTable.getModel();
        
        Object[] row = new Object[2];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getLocation();
             row[1]=list.get(i).getTimeTaken();
         
             
           
             model.addRow(row);
             
         }
    }
      public void displayTotalAfternoonSummary(JTable totalAfternoonTable){
              System.out.println("Display jobHasTask");
        ArrayList <TotalSummary> list = summaryTotalAfternoonList();
        
        DefaultTableModel model =(DefaultTableModel) totalAfternoonTable.getModel();
        
        Object[] row = new Object[2];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getLocation();
             row[1]=list.get(i).getTimeTaken();
         
             
           
             model.addRow(row);
             
         }
      }
      
     
    public void displayEveningSummary(JTable eveningShiftTable){
              System.out.println("Display jobHasTask");
        ArrayList <SummaryReport> list = summaryEveningList();
        
        DefaultTableModel model =(DefaultTableModel) eveningShiftTable.getModel();
        
        Object[] row = new Object[4];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getDate();
             row[1]=list.get(i).getLocation();
             row[2]=list.get(i).getTimeTaken();
             row[3]=list.get(i).getStartTime();
         
             
           
             model.addRow(row);
             
         }
    }
      
      
      
      
      
      
      
     
     
    public void displayAfternoonSummary(JTable afternoonShiftTable){
              System.out.println("Display jobHasTask");
        ArrayList <SummaryReport> list = summaryAfternoonList();
        
        DefaultTableModel model =(DefaultTableModel) afternoonShiftTable.getModel();
        
        Object[] row = new Object[4];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getDate();
             row[1]=list.get(i).getLocation();
             row[2]=list.get(i).getTimeTaken();
             row[3]=list.get(i).getStartTime();
         
             
           
             model.addRow(row);
             
         }
    }
    
    
    
    
    
       
    public void displayMorningSummary(JTable morningShiftTable){
              System.out.println("Display jobHasTask");
        ArrayList <SummaryReport> list = summaryMorningList();
        
        DefaultTableModel model =(DefaultTableModel) morningShiftTable.getModel();
        
        Object[] row = new Object[4];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getDate();
             row[1]=list.get(i).getLocation();
             row[2]=list.get(i).getTimeTaken();
             row[3]=list.get(i).getStartTime();
         
             
           
             model.addRow(row);
             
         }
    }
    
    
    
    
    public void displayIndividualPerformanceReport(JTable individualPerformanceReportTable){
              System.out.println("Display jobHasTask");
        ArrayList <IndividualPerformanceReport> list = individualPerformanceList();
        
        DefaultTableModel model =(DefaultTableModel) individualPerformanceReportTable.getModel();
        
        Object[] row = new Object[8];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getName();
             row[1]=list.get(i).getCode();
             row[2]=list.get(i).getTaskID();
             row[3]=list.get(i).getDepartment();
             row[4]=list.get(i).getDate();
             row[5]=list.get(i).getStartTime();
             row[6]=list.get(i).getTimeTaken();
             row[7]=list.get(i).getSum();
             
           
             model.addRow(row);
             
         }
    }
    
    public void displayIndividualJobReport(JTable jobReportTable){
              System.out.println("Display jobreport");
        ArrayList <IndividualJobReport> list = individualJobList();
        
        DefaultTableModel model =(DefaultTableModel) jobReportTable.getModel();
        
        Object[] row = new Object[8];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getAccountNo();
             row[1]=list.get(i).getJobCode();
             row[2]=list.get(i).getJobDescr();
             row[3]=list.get(i).getPrice();
             row[4]=list.get(i).getTaskID();
             
             
           
             model.addRow(row);
             
         }
    }
    
    public void displayIndividualJobReport(JTable jobReportTable,String accountNo){
              System.out.println("Display jobreport");
        ArrayList <IndividualJobReport> list = individualJobList();
        
        DefaultTableModel model =(DefaultTableModel) jobReportTable.getModel();
        
        Object[] row = new Object[8];
        
         for(int i = 0;i<list.size();i++){
             if(list.get(i).getAccountNo().contains(accountNo)){
                 
             
        
             row[0]=list.get(i).getAccountNo();
             row[1]=list.get(i).getJobCode();
             row[2]=list.get(i).getJobDescr();
             row[3]=list.get(i).getPrice();
             row[4]=list.get(i).getTaskID();
             
             
           
             model.addRow(row);
             }
         }
    }
    
    public void displayIndividualPerformanceReport(JTable individualPerformanceReportTable,String staffName){
              System.out.println("Display jobHasTask");
        ArrayList <IndividualPerformanceReport> list = individualPerformanceList();
        
        DefaultTableModel model =(DefaultTableModel) individualPerformanceReportTable.getModel();
        
        Object[] row = new Object[8];
        
         for(int i = 0;i<list.size();i++){
        if(list.get(i).getName().contains(staffName)){
             row[0]=list.get(i).getName();
             row[1]=list.get(i).getCode();
             row[2]=list.get(i).getTaskID();
             row[3]=list.get(i).getDepartment();
             row[4]=list.get(i).getDate();
             row[5]=list.get(i).getStartTime();
             row[6]=list.get(i).getTimeTaken();
             row[7]=list.get(i).getSum();
             
           
             model.addRow(row);
             
         }
        
        
    }
    
    }
    
       
    public void displayJobTask(JTable jobtask,int x){
              System.out.println("Display jobHasTask");
        ArrayList <JobTasks> list = jobTask();
        
        DefaultTableModel model =(DefaultTableModel) jobtask.getModel();
        
        Object[] row = new Object[3];
        
         for(int i = 0;i<list.size();i++){
        if(list.get(i).getJobNo() == x){
             row[0]=list.get(i).getJobNo();
             row[1]=list.get(i).getTaskID();
             row[2]=list.get(i).getStatus();
         
             
           
             model.addRow(row);
             
         }
    }
    }  
         public void displayJobTask(JTable jobtask,String x, int id){
              System.out.println("Display jobHasTask");
        ArrayList <JobTasks> list = jobTask();
        
        DefaultTableModel model =(DefaultTableModel) jobtask.getModel();
        
        Object[] row = new Object[3];
        
         for(int i = 0;i<list.size();i++){
        if(list.get(i).getStatus().contains(x)){
            if(list.get(i).getJobNo() == id){
             row[0]=list.get(i).getJobNo();
             row[1]=list.get(i).getTaskID();
             row[2]=list.get(i).getStatus();
         
             
           
             model.addRow(row);
            }
         }
    }
    }   
    public void displayJobTask(JTable jobtask){
              System.out.println("Display jobHasTask");
        ArrayList <JobTasks> list = jobTask();
        
        DefaultTableModel model =(DefaultTableModel) jobtask.getModel();
        
        Object[] row = new Object[3];
        
         for(int i = 0;i<list.size();i++){
      
             row[0]=list.get(i).getJobNo();
             row[1]=list.get(i).getTaskID();
             row[2]=list.get(i).getStatus();
         
             
           
             model.addRow(row);
             
         }
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
        
        Object[] row = new Object[6];
        
         for(int i = 0;i<list.size();i++){
        
             if(list.get(i).getJobNo() == jobNo){
             row[0]=list.get(i).getJobNo();
             row[1]=list.get(i).getJobCode();
             row[2]=list.get(i).getJobDescription();
             row[3]= list.get(i).getJobDate();
             row[4]= list.get(i).getCustomerID();
             row[5]= list.get(i).getJobStatus();
              model.addRow(row);
             
             } 
             
         }
       
    }
    
     
    
    
    public void displayJobs(JTable jobTable){
              System.out.println("Display Job");
        ArrayList <Job> list = jobList();
        
        DefaultTableModel model =(DefaultTableModel) jobTable.getModel();
        
        Object[] row = new Object[6];
        
         for(int i = 0;i<list.size();i++){
        
             row[0]=list.get(i).getJobNo();
             row[1]=list.get(i).getJobCode();
             row[2]=list.get(i).getJobDescription();
             row[3]= list.get(i).getJobDate();
             row[4]= list.get(i).getCustomerID();
                 row[5]= list.get(i).getJobStatus();
           
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
       
       public boolean setJobComplete(int id) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
       
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE job SET JobStatus = 'Completed' WHERE JobNo = ?");
          
          pst = conn.prepareStatement(update);
          
         
      
          pst.setInt(1,id);
          
      System.out.println("update to " +id );
          
          pst.executeUpdate();
          
          pst.close();
            
      }catch(Exception E){
          E.printStackTrace();
          return false;
      }
      
        return false; 
    }
      public boolean setJobInProgress(int id) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
       
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE job SET JobStatus = 'In-Progress' WHERE JobNo = ?");
          
          pst = conn.prepareStatement(update);
          
         
      
          pst.setInt(1,id);
          
      System.out.println("update to " +id );
          
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
      public boolean setTaskIDJob(int id,String State,int JobNO) throws Exception{
        
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
       
      try{
         // Connection conn = getConnection();
         
          String update =("UPDATE job_has_task SET State = ? WHERE Task_TaskID = ? AND Job_JobNo = ?");
          
          pst = conn.prepareStatement(update);
          
         
          pst.setString(1, State);
          pst.setInt(2,id);
          pst.setInt(3, JobNO);
      System.out.println("update to " +id + State);
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

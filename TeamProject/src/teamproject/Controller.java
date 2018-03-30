/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import teamproject.Accounts.AccountController;
import teamproject.Database.Jdbc;
import teamproject.Tasks.Task;
/**
 *
 * @author Ramee
 */
public class Controller {
    Login login;
    Bapers bapers;
    Jdbc jdbc;
    AccountController accController;
    Logout logout;
    //jtable selected row
    int selectedRow;
    
    public Controller(Login login,Bapers bapers,Jdbc jdbc,Logout logout){
    this.login = login;
    this.bapers = bapers;
    this.jdbc = jdbc;
    this.logout=logout;
    
    
    
    //action Listener 
    addLoginListener();
    addBapersListener();
    addLogoutListener();
    
    //shows current logged in user
    
    jdbc.displayCustomer(bapers.getCustomerTable());
    jdbc.displayTasks(bapers.getTaskTable());
    }
    
    public void addBapersListener(){
        //adds navigation for menu bar
        bapers.navigationBar(new BapersMenu());
        
        //adds navigation everything else
        bapers.CustomerPanelNavigation(new CustomerPanelNavigation());
        bapers.TaskPanelNavigation(new TaskPanelNavigation());
        
        //adds navigation for the customerMain page
        bapers.CustomerMain(new CustomerMain());
        bapers.TaskMain(new TaskMain());
       
        
        bapers.getTaskTable().addMouseListener(new MouseAdapter(){
        
         public void mouseClicked(MouseEvent e) {
                
                
                JTable target = bapers.getTaskTable();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
               
            }
        
        
        });
        
        
        
        
        
        
        //adds lisenter for the jTable in customerMain panel
        bapers.getCustomerTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                
                JTable target = bapers.getCustomerTable();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
                int id = (int) target.getValueAt(row, 0);
                System.out.println(id);
                selectedRow = id;
            }
       
        });
                
        bapers.createCustomer(new CreateNewCustomer());         
      
    }
    
    public void addLogoutListener(){
        logout.addLogoutAuth(new LogoutAuth());
    }
    
    public void addLoginListener(){
        login.addLoginAuth(new LoginAuth());
    }
 
    public void displayTaskTable(){
        jdbc.displayTasks(bapers.getTaskTable());
    }
    
    
    public void displayCustomerTable(){
    
    jdbc.displayCustomer(bapers.getCustomerTable());
    }
    
 class LogoutAuth implements ActionListener{
       @Override
        public void actionPerformed(ActionEvent e) {
          
            logout.dispose();
            login.show(true);
            
        }
 }
    
    //login actionlistener 
    class LoginAuth implements ActionListener{

        
        @Override
        public void actionPerformed(ActionEvent e) {
           
                try {
           
                    if( jdbc.validate_login(login.getUsername(), login.getPassword())==true){
                    
                    login.dispose();
                
                    bapers.setVisible(true);
                    bapers.setUser(login.getUsername());
                    
                    
                
                     }else{
                     login.wrongPassword();
                     System.out.println("Wrong password");
                    }
                
         
                
                
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
    }
    
    }
    
    
    
    //menubar navigation ActionLisenter
    class BapersMenu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
                if(e.getActionCommand().contains("Logout")){
                    
                    bapers.dispose();
                   
                    logout.setVisible(true);
                    System.out.println("logout");
                    
                }else{
                  
                    //selects the panels depending on which button is clicked
                 bapers.setpanel(e.getActionCommand());
                 
                 if(e.getActionCommand().contains("customer")){
                 bapers.setPanelCustomer("customerMain");
                 
                 }
                 
                 if(e.getActionCommand().contains("tasks")){
                     bapers.setPanelTask("taskMain");
                 }
                    System.out.println(e.getActionCommand());
                  // System.out.println(e.getActionCommand());
                }  
            }
                
                    
    }
    //navigation for customerPanel(homepage)
    class CustomerPanelNavigation implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
     
              System.out.println(e.getActionCommand());
           if(e.getActionCommand().contains("Customers")){
               bapers.setPanelCustomer("customerMain");
           }
           
           if(e.getActionCommand().contains("Add Customer")){
               
           bapers.setPanelCustomer("addnewcustomerCard");
           
           
           }
           if(e.getActionCommand().contains("Customer Info")){
           
        
           bapers.setPanelCustomer("customerinfo");
           
           }
            
            
     
      //  bapers.setPanelTest();
           
           
            
        }
    
    }
    
     class TaskPanelNavigation implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent p) {
      bapers.setpanel(p.getActionCommand());
                 
              System.out.println(p.getActionCommand());
           if(p.getActionCommand().contains("tasks")){
            bapers.setPanelTask("taskMain");
           }
             
        }
    
     }
    
    
     
    class TaskMain implements ActionListener{
         JTable taskTable = bapers.getTaskTable();
        
        DefaultTableModel model =(DefaultTableModel) taskTable.getModel();
      
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().contains("Edit Task")){
                System.exit(0);
            }
            if(e.getActionCommand().contains("Add Task")){
                System.exit(0);
            }
        }
    }
    
    class CustomerMain implements ActionListener{
        JTable customerTable = bapers.getCustomerTable();
        
        DefaultTableModel model =(DefaultTableModel) customerTable.getModel();
        
        @Override
        public void actionPerformed(ActionEvent e) {
        
            if(e.getActionCommand().contains("Upgrade")){
                
                try {
                    jdbc.setStatus(selectedRow, "valued");
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
                 if(e.getActionCommand().contains("Downgrade")){
                
                try {
                    jdbc.setStatus(selectedRow, "Normal");
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
                if(e.getActionCommand().contains("Create New Account")){
            
                 bapers.setPanelCustomer("addnewcustomerCard");
            
            
            }
              
            if(bapers.getValuedRadioButton().isSelected()){
                
                //removes existing data and displays new data
                model.setRowCount(0);
                
                
              
              
                 jdbc.displayCustomer(customerTable,"valued");
                 
                 
            }
            
            if(bapers.getNormalRadioButton().isSelected()){
                
                 //removes existing data and displays new data
                 model.setRowCount(0);
                 
                  jdbc.displayCustomer(customerTable,"Normal");
               
                
             
            }
            
            if(bapers.getInDefaultRadioButton().isSelected()){
                 //removes existing data and displays new data
                 model.setRowCount(0);
                 
               //removes existing data and displays new data
                jdbc.displayCustomer(customerTable,"in-default");
       
           
            }
            
            if(bapers.getAllRadioButton().isSelected()){
                  model.setRowCount(0);
                
                 jdbc.displayCustomer(customerTable);
               
            }
            
            
            
            
        }
    
    }    
    //navigation for men
    
    class CreateNewCustomer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(bapers.getCustomerName());
                   
        }
    
    
    
    }
   
}

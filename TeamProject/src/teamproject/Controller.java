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
import teamproject.Jobs.Job;

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
    jdbc.displayJobs(bapers.getJobTable());
    jdbc.displayPayment(bapers.getPaymentTable());
    jdbc.displayStaff(bapers.getStaffTable());
    
    }
    
    public void addBapersListener(){
        //adds navigation for menu bar
        bapers.navigationBar(new BapersMenu());
        
        //adds navigation everything else
        bapers.CustomerPanelNavigation(new CustomerPanelNavigation());
        bapers.TaskPanelNavigation(new TaskPanelNavigation());
        bapers.JobPanelNavigation(new JobPanelNavigation());
        bapers.PaymentPanelNavigation(new PaymentPanelNavigation());
        bapers.AdminPanelNavigation(new AdminPanelNavigation());

        
        //adds navigation for the customerMain page
        bapers.CustomerMain(new CustomerMain());
        bapers.TaskMain(new TaskMain());
        bapers.JobMain(new JobMain());
        bapers.PaymentMain(new PaymentMain());
        bapers.AdminMain(new AdminMain());
       
        
        bapers.getTaskTable().addMouseListener(new MouseAdapter(){
        
         public void mouseClicked(MouseEvent e) {
                
                
                JTable target = bapers.getTaskTable();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
               
            }

       
        });
        
         bapers.getStaffTable().addMouseListener(new MouseAdapter(){
        
         public void mouseClicked(MouseEvent e) {
                
                
                JTable target = bapers.getStaffTable();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
                int id = (int) target.getValueAt(row, 0);
                System.out.println(id);
                selectedRow = id;
               
            }

        });
        
        
        
        
        
        
        
        
        
       
        
        
        
                bapers.getPaymentTable().addMouseListener(new MouseAdapter(){
        
         public void mouseClicked(MouseEvent e) {
                
                
                JTable target = bapers.getPaymentTable();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
               
            }

        });
        
        
        
        
        
        
          bapers.getJobTable().addMouseListener(new MouseAdapter(){
        
         public void mouseClicked(MouseEvent e) {
                
                
                JTable target = bapers.getJobTable();
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
        bapers.createTask(new CreateNewTask());        
      
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
    
    
    public void displayPaymentTable(){
    
    jdbc.displayPayment(bapers.getPaymentTable());
    }
    
    
    
    public void displayJobTable(){
        jdbc.displayJobs(bapers.getJobTable());
    }
    
    public void displayStaffTable(){
        jdbc.displayStaff(bapers.getStaffTable());
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
           
                    if( jdbc.validate_login(login.getUsername(), login.getPassword(),login.getRole())==true){
                    
                       if("Receptionist".equals(login.getRole()) || "receptionist".equals(login.getRole())){
                    login.dispose();
                    
                    bapers.setVisible(true);
                   bapers.setPanelRecep(e.getActionCommand());
                    bapers.setUser(login.getRole());
                       }
                       
                       if("Technician".equals(login.getRole()) || "technician".equals(login.getRole())){
                              login.dispose();
                    
                    bapers.setVisible(true);
                   bapers.setPanelTech(e.getActionCommand());
                    bapers.setUser(login.getRole());
                       }
                       if("Shift Manager".equals(login.getRole()) || "shift manager".equals(login.getRole())){
                              login.dispose();
                    
                    bapers.setVisible(true);
                   bapers.setPanelShift(e.getActionCommand());
                    bapers.setUser(login.getRole());
                       }
                       if("Office Manager".equals(login.getRole()) || "office manager".equals(login.getRole())){
                              login.dispose();
                    
                    bapers.setVisible(true);
                   bapers.setPanelOffice(e.getActionCommand());
                    bapers.setUser(login.getRole());
                       } 
                       
                       
                       
                       
                    }else{
                     login.wrongPassword();
                     
                     System.out.println("Incorrect credentials");
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
           
                    
                    if("Receptionist".equals(login.getRole()) || "receptionist".equals(login.getRole())){
                        bapers.setPanelRecep(e.getActionCommand());
                    }
                    
                   if("Technician".equals(login.getRole())){
                        bapers.setPanelTech(e.getActionCommand());
                    }
                    
                   if("Shift Manager".equals(login.getRole()) || "shift manager".equals(login.getRole())){
                              
                    
                   
                   bapers.setPanelShift(e.getActionCommand());
                   
                   }
                   
                     if("Office Manager".equals(login.getRole()) || "office manager".equals(login.getRole())){
                      login.dispose();
                    
                    bapers.setVisible(true);
                   bapers.setPanelOffice(e.getActionCommand());
                    bapers.setUser(login.getRole());
                       } 
                 
                     if(e.getActionCommand().contains("Admin")){
                     bapers.setPanelAdmin("card4");
                 }
                     
                 if(e.getActionCommand().contains("customer")){
                
                     bapers.setPanelCustomer("customerMain");
                 
                 }
                 
                 if(e.getActionCommand().contains("tasks")){
                     bapers.setPanelTask("taskMain");
                 }
                  
                 if(e.getActionCommand().contains("payments")){
                     bapers.setPanelPayment("paymentMain");
                 }
                 
                 if(e.getActionCommand().contains("jobs")){
                     bapers.setPanelJob("jobMain");
                 }
                  

                
                 
                    System.out.println(e.getActionCommand());
                  // System.out.println(e.getActionCommand());
                }  
            }
                
                    
    }
    
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
    
    
    class AdminPanelNavigation implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent a) {
     bapers.setpanel(a.getActionCommand());
              System.out.println(a.getActionCommand());
           if(a.getActionCommand().contains("Admin")){
                     bapers.setPanelAdmin("card4");
                 }
                     
          
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
         
           
           if(p.getActionCommand().contains("addtask")){
               bapers.setPanelTask("addNewTask");
           }
           
          
           
        }
    
     }
     
     
     
     
     
     class PaymentPanelNavigation implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent p) {
         bapers.setpanel(p.getActionCommand());
             
                  
              System.out.println(p.getActionCommand());
           if(p.getActionCommand().contains("View Payments")){
            bapers.setPanelPayment("payment");
           }
         
           
          
           
        }
    
     }
     
     
     
          class JobPanelNavigation implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent p) {
         bapers.setpanel(p.getActionCommand());
             
                  
              System.out.println(p.getActionCommand());
           if(p.getActionCommand().contains("View Jobs")){
            bapers.setPanelJob("viewJob");
           }
         
           
          
           
        }
    
     }
     
     class AdminMain implements ActionListener{
         JTable staffTable = bapers.getStaffTable();
                DefaultTableModel model =(DefaultTableModel) staffTable.getModel();
                 @Override
        public void actionPerformed(ActionEvent e) {
             if(e.getActionCommand().contains("Edit Role")){
                
                try {
                    jdbc.setRole(selectedRow, bapers.getStaffRole().getText());
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
             }
             
               if(e.getActionCommand().contains("Edit Staff ID")){
                
                try {
                    jdbc.setStaffID(selectedRow, Integer.parseInt(bapers.getStaffID().getText()));
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
             
                if(e.getActionCommand().contains("Edit Forename")){
                
                try {
                    jdbc.setStaffFirstName(selectedRow, bapers.getStaffFirstName().getText());
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
             
                if(e.getActionCommand().contains("Edit Surename")){
                
                try {
                    jdbc.setStaffLastName(selectedRow, bapers.getStaffLastName().getText());
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
             
               
                  if(e.getActionCommand().contains("Delete Account")){
                
                try {
                    jdbc.setDeletion(selectedRow);
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
             
             
             
             if(bapers.getReceptionistRadio().isSelected()){
                 //removes existing data and displays new data
                 model.setRowCount(0);
                 
               //removes existing data and displays new data
                jdbc.displayStaff(staffTable,"Receptionist");
       
           
            }
             if(bapers.getShiftManagerRadio().isSelected()){
                 //removes existing data and displays new data
                 model.setRowCount(0);
                 
               //removes existing data and displays new data
                jdbc.displayStaff(staffTable,"Shift Manager");
       
           
            }
              if(bapers.getOfficeManagerRadio().isSelected()){
                 //removes existing data and displays new data
                 model.setRowCount(0);
                 
               //removes existing data and displays new data
                jdbc.displayStaff(staffTable,"Office Manager");
       
           
            }
              
               if(bapers.getTechnicianRadio().isSelected()){
                 //removes existing data and displays new data
                 model.setRowCount(0);
                 
               //removes existing data and displays new data
                jdbc.displayStaff(staffTable,"Technician");
       
           
            }
               
             
               if(bapers.getAllRadio().isSelected()){
                 //removes existing data and displays new data
                 model.setRowCount(0);
                 
               //removes existing data and displays new data
                jdbc.displayStaff(staffTable);
       
           
            }  
            
        }
     }

     
     
     
     class JobMain implements ActionListener{
         JTable jobTable = bapers.getJobTable();
                DefaultTableModel model =(DefaultTableModel) jobTable.getModel();
                 @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().contains("Edit")){
                System.exit(0);
            }
            
        }
     }
     
      class PaymentMain implements ActionListener{
         JTable paymentTable = bapers.getPaymentTable();
                DefaultTableModel model =(DefaultTableModel) paymentTable.getModel();
                 @Override
        public void actionPerformed(ActionEvent e) {
           
            
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
            if(e.getActionCommand().contains("Delete Task")){
                System.exit(0);
            }
            
            if(e.getActionCommand().contains("Add Task")){
            
                 bapers.setPanelTask("addNewTask");
            
            
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
    
    
    
    
    class CreateNewTask implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(bapers.getTaskID());
            System.out.println(bapers.getTaskDescription());
            System.out.println(bapers.getTaskLocation());
            System.out.println(bapers.getShelfSlot());
            System.out.println(bapers.getPrice());
            System.out.println(bapers.getDuration());
                   
        }
    
    
    
    }
    
    
   
   
}

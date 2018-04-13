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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JFileChooser;
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
    jdbc.displayJobTask(bapers.getJobTasksTable());
   
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
       bapers.createCustomerMain(new CreateCustomerMain());
       bapers.createTaskMain(new CreateTaskMain());
       bapers.JobTasks(new JobTasks());
        
        bapers.getTaskTable().addMouseListener(new MouseAdapter(){
        
         public void mouseClicked(MouseEvent e) {
                
                
                JTable target = bapers.getTaskTable();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
                int id = (int) target.getValueAt(row, 0);
                System.out.println(id);
                selectedRow = id;
                
               
               
            }
            
       
       
        });
        
         bapers.getJobTasksTable().addMouseListener(new MouseAdapter(){
        
         public void mouseClicked(MouseEvent e) {
                
                
                JTable target = bapers.getJobTasksTable();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
                int id = (int) target.getValueAt(row, 0);
                System.out.println(id);
                selectedRow = id;
               
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
                int id = (int) target.getValueAt(row, 0);
                System.out.println(id);
                selectedRow = id;
                JTable jobTasksTable = bapers.getJobTasksTable();
         
        DefaultTableModel model =(DefaultTableModel) jobTasksTable.getModel();
      
                if (e.getClickCount() == 2) {
                    bapers.setPanelJob("jobTasks");
                    model.setRowCount(0);
                     jdbc.displayJobTask(jobTasksTable,selectedRow );
                                   
                              
                     
                     
                     
                     
                    }
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

        String role = null;
        @Override
        public void actionPerformed(ActionEvent e) {
                try {
                    role = jdbc.retrieveRole(login.getUsername
        ());
                    try {
                        
                        if( jdbc.validate_login(login.getUsername(), login.getPassword())==true){
                            
                            if("Receptionist".equals(role) || "receptionist".equals(role)){
                                login.dispose();
                                
                                bapers.setVisible(true);
                                bapers.setPanelRecep(e.getActionCommand());
                                bapers.setUser(role);
                            }
                            
                            if("Technician".equals(role) || "technician".equals(role)){
                                login.dispose();
                                
                                bapers.setVisible(true);
                                bapers.setPanelTech(e.getActionCommand());
                                bapers.setUser(role);
                            }
                            if("Shift Manager".equals(role) || "shift manager".equals(role)){
                                login.dispose();
                                
                                bapers.setVisible(true);
                                bapers.setPanelShift(e.getActionCommand());
                                bapers.setUser(role);
                            }
                            if("Office Manager".equals(role) || "office manager".equals(role)){
                                login.dispose();
                                
                                bapers.setVisible(true);
                                bapers.setPanelOffice(e.getActionCommand());
                                bapers.setUser(role);
                            }
                            
                            
                            
                            
                        }else{
                            login.wrongPassword();
                            
                            System.out.println("Incorrect credentials");
                        }
                        
                    } catch (Exception ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
    }
    
    }
    
 
    
    
    
    
    

    //menubar navigation ActionLisenter
    class BapersMenu implements ActionListener{
        String role = null;
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                role = jdbc.retrieveRole(login.getUsername());
                if(e.getActionCommand().contains("Logout")){
                    
                    bapers.dispose();
                   
                    logout.setVisible(true);
                    System.out.println("logout");
                    
                }else{
                  
                    //selects the panels depending on which button is clicked
                    
                    
                    if("Receptionist".equals(role) || "receptionist".equals(role)){
                        bapers.setPanelRecep(e.getActionCommand());
                    }
                    
                    if("Technician".equals(role)){
                        bapers.setPanelTech(e.getActionCommand());
                    }
                    
                    if("Shift Manager".equals(role) || "shift manager".equals(role)){
                        
                        
                        
                        bapers.setPanelShift(e.getActionCommand());
                        
                    }
                    
                    if("Office Manager".equals(role) || "office manager".equals(role)){
                        login.dispose();
                        
                        bapers.setVisible(true);
                        bapers.setPanelOffice(e.getActionCommand());
                        bapers.setUser(role);
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
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
           if(p.getActionCommand().contains("Tasks")){
            bapers.setPanelTask("card2");
           }
         
           
           if(p.getActionCommand().contains("Add Task")){
               bapers.setPanelTask("card3");
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
         
         String path = "null";
         String filename;
         
         JTable staffTable = bapers.getStaffTable();
                DefaultTableModel model =(DefaultTableModel) staffTable.getModel();
                 @Override
        public void actionPerformed(ActionEvent e) {
            
            
            if(e.getActionCommand().contains("Search Staff")){
                try {
                                    bapers.isAdminListselected();
                                    model.setRowCount(0);
                                   
                   jdbc.displayStaff(staffTable, Integer.parseInt(bapers.getSearchStaff().getText()));
                                    
                                    
                                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                           
                                       }
                
            }
            
            
            if(e.getActionCommand().contains("Cancel")){
                System.exit(0);
            }
            
             if(e.getActionCommand().contains("Edit Role")){
                
                try {
                    jdbc.setRole(Integer.parseInt(bapers.getSearchStaff().getText()), bapers.getStaffRole().getSelectedItem().toString()    );
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
             }
             
       
               
             
                if(e.getActionCommand().contains("Edit Forename")){
                
                try {
                    jdbc.setStaffFirstName(Integer.parseInt(bapers.getSearchStaff().getText()), bapers.getStaffFirstName().getText());
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
             
                if(e.getActionCommand().contains("Edit Surename")){
                
                try {
                    jdbc.setStaffLastName(Integer.parseInt(bapers.getSearchStaff().getText()), bapers.getStaffLastName().getText());
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
             
                
                
                
               
                  if(e.getActionCommand().contains("Delete Account")){
                
                try {
                    jdbc.setDeletion(Integer.parseInt(bapers.getSearchStaff().getText()));
                  


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
             
                    if(e.getActionCommand().contains("Edit UserID")){
                
                try {
                     jdbc.setUserID(Integer.parseInt(bapers.getSearchStaff().getText()), bapers.getUserID().getText());


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
                   if(e.getActionCommand().contains("Edit Pass")){
                
                try {
                     jdbc.setPassword(Integer.parseInt(bapers.getSearchStaff().getText()), bapers.getPassword().getText());


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
               if(e.getActionCommand().contains("Add Account")){
                
                try {
                     jdbc.setNewAccount( bapers.getStaffRole().getSelectedItem().toString(),bapers.getStaffFirstName().getText(),bapers.getStaffLastName().getText(),bapers.getUserID().getText(),bapers.getPassword().getText());


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
          
            if(e.getActionCommand().contains("All Jobs")){
                try {
                   model.setRowCount(0);
                   jdbc.displayJobs(jobTable);
                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(e.getActionCommand().contains("View Tasks")){
                try {
                    bapers.setPanelJob("jobTasks");
                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            if(e.getActionCommand().contains("Jobs")){
            
            bapers.setPanelJob("card2");
            bapers.getSearchJobTaskID().setText("Write TaskID to Edit");
           
            }
            
            if(e.getActionCommand().contains("Search")){
                                try {
                                    model.setRowCount(0);
                 
                 jdbc.displayJob(jobTable,Integer.parseInt(bapers.getSearchJobNumber().getText()));
                 // jdbc.displayJob(jobTable,Integer.parseInt(bapers.searchToDeleteC().getText()));

                                    
                                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                             
                                
                                }
            }
            if(e.getActionCommand().contains("All jobs")){
                 try {
                                    model.setRowCount(0);
                 
                  jdbc.displayJobs(jobTable);
                                    
                                    
                                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                             
                                
                                }
            }
            
            if(e.getActionCommand().contains("Edit Job Code")){
                
                try {
                     jdbc.setJobCode(Integer.parseInt(bapers.getSearchJobNumber().getText()), Integer.parseInt(bapers.setJobCode().getText()));
                        

                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   
            
                if(e.getActionCommand().contains("Edit Job Description")){
                
                try {
                     jdbc.setJobDescription(Integer.parseInt(bapers.getSearchJobNumber().getText()), bapers.setJobDescr().getText());


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   
                
                
                  if(e.getActionCommand().contains("Edit Job Date")){
                
                try {
            
                     
                     jdbc.setJobDate(Integer.parseInt(bapers.getSearchJobNumber().getText()), bapers.setJobDate().getText());


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   
               if(e.getActionCommand().contains("Edit Customer ID")){
                
                try {
            
                     
                     jdbc.setCustomerID(Integer.parseInt(bapers.getSearchJobNumber().getText()), Integer.parseInt(bapers.setCustomerID().getText()));


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   
             if(e.getActionCommand().contains("Delete Job")){
                
                try {
            
                     
                     jdbc.setDeletionJob(Integer.parseInt(bapers.getSearchJobNumber().getText()));

                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
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
     
     class JobTasks implements ActionListener{
         JTable jobTasksTable = bapers.getJobTasksTable();
        
        DefaultTableModel model =(DefaultTableModel) jobTasksTable.getModel();
      
        @Override
        public void actionPerformed(ActionEvent e) {
           
            
            
            if(e.getActionCommand().contains("Save Edit")){
                                try {
                            
                 
                 jdbc.setTaskIDJob(Integer.parseInt(bapers.getSearchJobTaskID().getText()), bapers.getJobTaksCombo().getSelectedItem().toString(),selectedRow);
try {
                        if(jdbc.retrieveStatusComplete(selectedRow).contains("Complete") && !jdbc.retrieveStatusInProgress(selectedRow).contains("In-Progress") && !jdbc.retrieveStatusPending(selectedRow).contains("Pending")){
                            try {
                                jdbc.setJobComplete(selectedRow);
                            } catch (Exception ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                         if( jdbc.retrieveStatusInProgress(selectedRow).contains("In-Progress")){
                            try {
                                jdbc.setJobInProgress(selectedRow);
                            } catch (Exception ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if( jdbc.retrieveStatusInProgress(selectedRow).contains("In-Progress") && jdbc.retrieveStatusPending(selectedRow).contains("Pending")){
                            try {
                                jdbc.setJobInProgress(selectedRow);
                            } catch (Exception ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if( !jdbc.retrieveStatusInProgress(selectedRow).contains("In-Progress") && jdbc.retrieveStatusPending(selectedRow).contains("Pending")){
                            try {
                                jdbc.setJobInProgress(selectedRow);
                            } catch (Exception ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                
                } catch (SQLException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                                    
                                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                             
                                
                                }
                                
            } 
             
            
        }
      
     }
    class TaskMain implements ActionListener{
         JTable taskTable = bapers.getTaskTable();
        
        DefaultTableModel model =(DefaultTableModel) taskTable.getModel();
      
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().contains("All Tasks")){
                try {
                                    model.setRowCount(0);
                 
                   jdbc.displayTasks(taskTable);
                                    
                                    
                                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                             
                                
                                }
            }
            
            if(e.getActionCommand().contains("Search Task")){
                try {
                                    model.setRowCount(0);
                 
                   jdbc.displayTasks(taskTable, Integer.parseInt(bapers.getSearchTaskID().getText()));
                                    
                                    
                                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                           
                                       }
                
            }
           
            if(e.getActionCommand().contains("Delete Task")){
                try {
                     jdbc.setDeletionTask(Integer.parseInt(bapers.getSearchTaskID().getText()));

                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
           if(e.getActionCommand().contains("Edit Description")){
               try {
                     jdbc.setTaskDescription(Integer.parseInt(bapers.getSearchTaskID().getText()),bapers.getTaskDescriptionEdit().getText());

                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
           
           
           if(e.getActionCommand().contains("Edit Location")){
               try {
                     jdbc.setTaskLocation(Integer.parseInt(bapers.getSearchTaskID().getText()),bapers.editLocation().getSelectedItem().toString());

                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
            
           
           
           if(e.getActionCommand().contains("Edit Shelf Slot")){
               try {
                     jdbc.setTaskShelfSlot(Integer.parseInt(bapers.getSearchTaskID().getText()),bapers.setShelfSlot().getText());

                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
           
           if(e.getActionCommand().contains("Edit Price")){
               try {
                     jdbc.setTaskPrice(Integer.parseInt(bapers.getSearchTaskID().getText()),Float.parseFloat(bapers.setPrice().getText()));

                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
           
           
           if(e.getActionCommand().contains("Edit Duration")){
               try {
                     jdbc.setTaskDuration(Integer.parseInt(bapers.getSearchTaskID().getText()),Integer.parseInt(bapers.setDuration().getText()));

                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
                
        }
    }
    
    class CreateTaskMain implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().contains("Save task")){
                try {
                     jdbc.createTask(bapers.getTaskDescription().getText(),bapers.getLocationTask().getSelectedItem().toString(),bapers.getShelfSlot().getText(),Float.parseFloat(bapers.getPrice().getText()),Integer.parseInt(bapers.getDuration().getText()),Integer.parseInt(bapers.getVariableID().getText()));


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
            
            
             
            
            
            
            
    }
    }
    
    class CreateCustomerMain implements ActionListener{
         @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().contains("Create Account")){
                
                try {
                     jdbc.setNewCustomerAccount(bapers.getAccountNumber(),bapers.GetFirstName().getText(), bapers.getSurename().getText(),bapers.getFirstAddress().getText(),bapers.getPostcode().getText(),bapers.getPhoneNumber().getText(),bapers.getDiscountCombo().getSelectedItem().toString(),bapers.getStatusCombo().getSelectedItem().toString());


                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   
            
            
            if(e.getActionCommand().contains("Cancel")){
                bapers.GetFirstName().setText(null);
                bapers.getSurename().setText(null);
                bapers.getFirstAddress().setText(null);
                bapers.getPostcode().setText(null);
                bapers.getPhoneNumber().setText(null);
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
            
            
                  if(e.getActionCommand().contains("Delete Customer")){
                
                try {
                   
                    jdbc.setDeletionCustomer(Integer.parseInt(bapers.searchToDeleteC().getText()));
                  


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
                
                
              
              
                 jdbc.displayCustomer(customerTable,"Valued");
                 
                 
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
            
             
            if(e.getActionCommand().contains("Generate In-Default")){
                 //removes existing data and displays new data
                 try {
                   model.setRowCount(0);
                 
               //removes existing data and displays new data
                jdbc.displayCustomer(customerTable,"in-default");
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }  
       
           
            }
            
            
            if(bapers.getAllRadioButton().isSelected()){
                  model.setRowCount(0);
                
                 jdbc.displayCustomer(customerTable);
               
            }
            
            
            
            
        }
    
    }    
    //navigation for men
    
    
    
    
    
    
    
    
    
   
   
}

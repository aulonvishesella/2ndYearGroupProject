/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Reports;

/**
 *
 * @author Ardit
 */
public class IndividualJobReport {
 String accountNo;
 String jobCode;
 String jobDescr;
 float price;
 int taskID;
 
 public IndividualJobReport(String accountNo,String jobCode,String jobDescr,float price,int taskID){
     this.accountNo=accountNo;
     this.jobCode=jobCode;
     this.jobDescr=jobDescr;
     this.price=price;
     this.taskID=taskID;
 }
 
 public String getAccountNo(){
     return accountNo;
 }
 
 public String getJobCode(){
     return jobCode;
 }
 
 public String getJobDescr(){
     return jobDescr;
 }
 
 public float getPrice(){
     return price;
 }
 
 public int getTaskID(){
     return taskID;
 }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Jobs;

import java.sql.Date;

/**
 *
 * @author Aulon
 */
public class Job {
    private int jobNo;
    private String jobCode;
    private String jobDescription;
    private Date jobDate;
    private int customerID;
    private String JobStatus;
    
    
    
    
    
      
    public Job(int jobNo,String jobCode,String jobDescription, Date jobDate, int customerID,String JobStatus){
        this.jobNo=jobNo;
        this.jobCode=jobCode;
        this.jobDescription=jobDescription;
        this.jobDate=jobDate;
        this.customerID=customerID;
        this.JobStatus = JobStatus;
    }

  
    
    
    
    public int getJobNo(){
        return jobNo;
    }
    
    public String getJobCode(){
        return jobCode;
    }
    public String getJobDescription(){
        return jobDescription;
    }
    
    public Date getJobDate(){
        return jobDate;
    }
    
    public int getCustomerID(){
        return customerID;
    }
    
    public String getJobStatus(){
    return JobStatus;
    }
    
    
    
   
}

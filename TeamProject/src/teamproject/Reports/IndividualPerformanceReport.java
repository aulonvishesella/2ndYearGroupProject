/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Reports;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Ardit
 */
public class IndividualPerformanceReport {
    private String name;
    private String code;
    private int taskID;
    private String department;
    private Date date;
    private String startTime;
    private int timeTaken;
    private int sum;
    
    
    public IndividualPerformanceReport(String name,String code,int taskID, String department,Date date, String startTime,int timeTaken,int sum){
        this.name=name;
        this.code=code;
        this.taskID=taskID;
        this.department=department;
        this.date=date;
        this.startTime=startTime;
        this.timeTaken=timeTaken;
        this.sum=sum;
    }
    
    public String getName(){
        return name;
    }
    
    public String getCode(){
        return code;
    }
    
    public int getTaskID(){
        return taskID;
    }
    
    public String getDepartment(){
        return department;
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getStartTime(){
        return startTime;
    }
    
    public int getTimeTaken(){
        return timeTaken;
    }
    
    public int getSum(){
        return sum;
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Reports;

import java.util.Date;

/**
 *
 * @author Ardit
 */
public class SummaryReport {
   private Date date;
   private String location;
   private int timeTaken;
   private String startTime;
   
   public SummaryReport(Date date, String location,int timeTaken,String startTime){
       this.date=date;
       this.location=location;
       this.timeTaken=timeTaken;
       this.startTime=startTime;
   }
   
   public Date getDate(){
       return date;
   }
   
   public String getLocation(){
       return location;
   }
   
   public int getTimeTaken(){
       return timeTaken;
   }
   
   public String getStartTime(){
       return startTime;
   }
}

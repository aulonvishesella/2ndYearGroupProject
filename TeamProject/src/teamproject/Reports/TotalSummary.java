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
public class TotalSummary {
    private String location;
    private int timeTaken;
    
    public TotalSummary(String location,int timeTaken){
        this.location=location;
        this.timeTaken=timeTaken;
    }
    
    public String getLocation(){
        return location;
    }
    
    public int getTimeTaken(){
        return timeTaken;
    }
}

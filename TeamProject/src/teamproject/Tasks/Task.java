/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Tasks;

/**
 *
 * @author Ardit
 */
public class Task {
    private int taskID;
    private String taskDescription;
    private String location;
    private String shelfSlot;
    private float price;
    private int duration;
    
    
    
    public Task(int taskID,String taskDescription,String location, String shelfSlot, float price, int duration){
        this.taskID=taskID;
        this.taskDescription=taskDescription;
        this.location=location;
        this.shelfSlot=shelfSlot;
        this.price=price;
        this.duration=duration;
    }

 
    public int getTaskID(){
        return taskID;
    }
    public String getTaskDescription(){
        return taskDescription;
    }
    
    public String getLocation(){
        return location;
        
    }
    
    public String getShelfSlot(){
        return shelfSlot;
    }
    
    public double getPrice(){
        return price;
    }
    public int getDuration(){
        return duration;
    }
    
}

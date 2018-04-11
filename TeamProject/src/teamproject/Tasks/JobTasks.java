/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Tasks;

/**
 *
 * @author rameez
 */
public class JobTasks {

private int taskID;

private String status;
private int jobNo;

public JobTasks(int taskID,String status,int jobNo){

this.taskID = taskID;

this.status = status;
this.jobNo=jobNo;


}
public int getJobNo(){
    return jobNo;
}
public int getTaskID(){

return taskID;
      
}


public String getStatus(){
return status;
}



}

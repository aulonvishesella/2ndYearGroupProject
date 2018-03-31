/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.Payment;

/**
 *
 * @author Ardit
 */
public class PaymentRecord {
    private int paymentID;
    private String paymentType;
    private float paymentAmount;
    private int jobNo;
    
    public PaymentRecord(int paymentID,String paymentType, float paymentAmount, int jobNo){
        this.paymentID= paymentID;
        this.paymentType = paymentType;
        this.paymentAmount=paymentAmount;
        this.jobNo=jobNo;
    }
    
    public int getPaymentID(){
        return paymentID;
    }
    public String getPaymentType(){
        return paymentType;
    }
    
    public float getPaymentAmount(){
        return paymentAmount;
    }
    
    public int getJobNo(){
        return jobNo;
    }
    
}

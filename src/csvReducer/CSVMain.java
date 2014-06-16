/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csvReducer;

import java.io.IOException;


public class CSVMain {

    public static void main(String[] args) throws IOException  {
        
        System.out.println("Converting...");
        //number of data,number of max data,input,output
        CSVSplit.randomPickTestTreningSplit(20000,"DataFraud100k.csv", "DataFraud100kTest.csv","DataFraud100kTrening.csv");
        System.out.println("Converted");
    }
}
    

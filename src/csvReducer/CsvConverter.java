/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csvReducer;

import java.io.IOException;


public class CsvConverter {

 
    public static void main(String[] args) throws IOException  {
        
        System.err.println("Converting...");
        Convert.randomPick(1000000, 10000000, "ccFraud.csv", "DataFraud1MWNF.csv");
        System.err.println("Converted");
    }
}
    

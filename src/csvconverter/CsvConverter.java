/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csvconverter;

import java.io.IOException;


public class CsvConverter {

 
    public static void main(String[] args) throws IOException  {
        
        System.err.println("Converting...");
        Convert.randomPick(100000, 10000000, "ccFraud.csv", "dule100k");
        System.err.println("Converted");
    }
}
    

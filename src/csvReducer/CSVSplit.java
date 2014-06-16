/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csvReducer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author ivan
 */
public class CSVSplit {
    
    public static void randomPick(int lineNumber,int max,String inputFile,String outputfile) throws FileNotFoundException, IOException{
    
        
        BufferedReader br = new BufferedReader(new FileReader("Data/"+inputFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+outputfile));
       
        String line;
        
        Random rng = new Random(); // Ideally just create one instance globally
        // Note: use LinkedHashSet to maintain insertion order
        Set<Integer> generated = new LinkedHashSet<>();
        
        while (generated.size() < lineNumber)
        {
            Integer next = rng.nextInt(max) + 1;
            // As we're adding to a set, this will automatically do a containment check
            generated.add(next);
            
            
        }
        
        //bw.write(br.readLine() + "\n"); //write first line,features names 
        br.readLine();                      //skip one line,description fields
        while((line = br.readLine()) != null)
        {
            
            String[] value = line.split(",");
            int lineNum = Integer.parseInt(value[0]);
            if(generated.contains(lineNum))
            {
              bw.write(line + "\n");
            }
            
        }
        
        br.close();
        bw.close();
      
    }
    
    public static void randomPickTestTreningSplit(int max,String inputFile,String outputfileTest,String outputFileTrening) throws FileNotFoundException, IOException{
    
        
        String firstLine;
        String line;
        
        BufferedReader br = new BufferedReader(new FileReader("Data/"+ inputFile));
        BufferedWriter bwTest = new BufferedWriter(new FileWriter("Data/"+outputfileTest));
        BufferedWriter bwTrening = new BufferedWriter(new FileWriter("Data/"+outputFileTrening));
        
       
       
        //read column names("id","gender"...)
        firstLine = br.readLine()+"\n";
        bwTest.write(firstLine); 
        bwTrening.write(firstLine);
        
        Integer counter = 1;
        
        while((line = br.readLine())!= null){
            
            if(counter < max)
                bwTest.write(line +"\n");
            else
                bwTrening.write(line+"\n");
            
            counter++;
        }
        
        br.close();
        bwTest.close();
        bwTrening.close();
    }
}

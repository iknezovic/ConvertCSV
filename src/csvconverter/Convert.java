/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csvconverter;

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
 * @author Fqsbs
 */
public class Convert {
    
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
        br.readLine();
        
        while((line = br.readLine()) != null)
        {
            //line = br.readLine();
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
    }
    
    


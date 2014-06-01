/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csvToSequence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.NamedVector;
import org.apache.mahout.math.VectorWritable;

/**
 *
 * @author ivan
 */
public class ConvertToSequenceTxtTxt {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String filename = "/home/ivan/WorkDir/DataFraud100kWNF.csv";
        String outputfilename = "/home/ivan/WorkDir/part-0000";
      
        SequenceFile.Writer writer;
        Configuration conf = new Configuration();
        
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s;
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(outputfilename);
        
        writer = new SequenceFile.Writer(fs, conf, path, Text.class, Text.class);
        Text key = new Text();
        Text value = new Text();
        
        while((s = br.readLine()) != null){
           String[] split = s.split(",");
           if(Integer.parseInt(split[8]) == 1)
               key.set("Fraud/1");
           else
               key.set("Normal/0");
          
           value.set(s.replace(",", " "));
           writer.append(key, value);
           
           
        }
        writer.close();
        
        try (SequenceFile.Reader reader = new SequenceFile.Reader(fs,path, conf)) {
           
            while (reader.next(key, value)) {
                
                System.out.println( key+" "+value);
            }
      }
    }
}


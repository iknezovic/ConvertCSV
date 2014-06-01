/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csvToSequence;

import java.io.BufferedReader;
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
public class ConvertToSeqLargeTxtVec {
    public static void main(String[] args) throws IOException {
        String filename = "/home/ivan/WorkDir/ccFraud.csv";
        String outputfilename = "/home/ivan/WorkDir/part-0000";
        
        
        SequenceFile.Writer writer;
        Configuration conf = new Configuration();
        
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(outputfilename);
        
        writer = new SequenceFile.Writer(fs, conf, path, Text.class, VectorWritable.class);
        VectorWritable  vec= new VectorWritable();
        
        
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s;
        br.readLine();          //skip line
        
        while((s = br.readLine()) != null){
           String[] value = s.split(",");
           double []numValue = new double[8];
             
           for(int i = 0; i < 8 ; i++)
              numValue[i] = Double.parseDouble(value[i]);
           
           if(Integer.parseInt(value[8]) == 1)
               value[8]="Fraud/"+value[8];
           else
               value[8]="Normal/"+value[8];
           
           NamedVector oneV = new NamedVector(new DenseVector(numValue),value[8]);
           
           vec.set(oneV.getDelegate());
            writer.append(new Text(oneV.getName()), vec);
           
           
        }
        writer.close();
    }
    
}

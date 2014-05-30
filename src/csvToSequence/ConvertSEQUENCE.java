/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csvToSequence;

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
import org.apache.mahout.math.NamedVector;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.utils.vectors.csv.CSVVectorIterator;

/**
 *
 * @author ivan
 */
public class ConvertSEQUENCE {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        String filename = "/home/ivan/WorkDir/DataFraud100kWNF.csv";
        String outputfilename = "/home/ivan/WorkDir/part-0000";
        
        
        SequenceFile.Writer writer;
        Configuration conf = new Configuration();
        
        CSVVectorIterator vectorCSVVectorIterator = new CSVVectorIterator(new FileReader(filename));
        //System.out.println("Densvector"+vec.next()):
        List<NamedVector> namedVectors = new ArrayList<>();
        Integer i = 1;
        
        while(vectorCSVVectorIterator.hasNext()){
            NamedVector vecIt = new NamedVector(vectorCSVVectorIterator.next(),i.toString());
            namedVectors.add(vecIt);
            i++;
        }
        
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(outputfilename);
        
        writer = new SequenceFile.Writer(fs, conf, path, Text.class, VectorWritable.class);
        
        VectorWritable  vec= new VectorWritable();
       
        for(NamedVector iter: namedVectors)
        {
            vec.set(iter);
            writer.append(new Text(iter.getName()), vec);
        }
        
        writer.close();
        
        try (SequenceFile.Reader reader = new SequenceFile.Reader(fs,path, conf)) {
            Text key = new Text();
            VectorWritable value = new VectorWritable();
            while (reader.next(key, value)) {
                
                System.out.println(key.toString() + " "+ value.get().asFormatString());
            }
        }
        
     }
             
   }
    


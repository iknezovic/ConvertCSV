/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package readSequence;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.math.VectorWritable;

/**
 *
 * @author ivan
 */
public class read {
    public static void main(String[] args) throws IOException {
        String outputfilename = "/home/ivan/WorkDir/ExampleBayes/20nes-seq/part-m-00000";
        
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(outputfilename);
        
    
            SequenceFile.Reader reader = new SequenceFile.Reader(fs,path, conf); 
            Text key = new Text();
            //VectorWritable value = new VectorWritable();
            Text value = new Text();
            while (reader.next(key, value)) {
                
                System.out.println(" "+value);
            }
    }
    
}

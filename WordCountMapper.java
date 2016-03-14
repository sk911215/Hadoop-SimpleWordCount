import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper ;

public class WordCountMapper extends Mapper<Object, Text,Text, IntWritable> {

	
	private final static IntWritable one = new IntWritable(1);
	
	public void map(Object key, Text value, Context output) throws IOException,InterruptedException{
		
		
		String[] words = value.toString().split(" ");
		
		output.write(new Text(words[0]), one);
			
		
	}
	
}

import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	int freq = 0;
	ArrayList<Text> listT = new ArrayList<Text>(); 

	protected void reduce(Text key, Iterable<IntWritable> values,
			Context output) throws IOException, InterruptedException {
		
		int voteCount = 0;
		
		for(IntWritable value: values){
			voteCount += value.get();
		}
		
		
		if(voteCount == freq)
		{
			freq = voteCount;
			Text freqWord = new Text();
			freqWord.set(key);
			listT.add(freqWord);
		}
		else if (voteCount > freq) {
			listT.clear();
			freq = voteCount;
			Text freqWord = new Text();
			freqWord.set(key);
			listT.add(freqWord);
		}
	}
	
	
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context output)
			throws IOException, InterruptedException {
		
		 	for(int i=0;i<listT.size();i++)
		 	{
		 		output.write(listT.get(i), new IntWritable(freq));
		 	}
	            
		
	}

	
}

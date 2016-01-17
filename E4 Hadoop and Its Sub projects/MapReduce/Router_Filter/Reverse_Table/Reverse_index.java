
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class Reverse_index extends Configured implements Tool{
	
	enum Counter
	{
		LINESKIP,
	}
	public static class Map extends Mapper<LongWritable, Text, Text, Text>
	{
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
		{
			String line = value.toString();
			
			try
			{
				String [] lineSplit = line.split(" ");
				String anum = lineSplit[0];
				String bnum = lineSplit[1];

				context.write(new Text(bnum),new Text(anum));
			}
			catch(java.lang.ArrayIndexOutOfBoundsException e)
			{
				context.getCounter(Counter.LINESKIP).increment(1);
				return;
			}
		}
	
	}
	
	public static class Reduce extends Reducer<Text, Text, Text,Text> 
	{
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
		{
			String valueString;
			String out = "";
			for(Text value: values)
			{
				valueString = value.toString();
				out += valueString + "|";
			}
			
			context.write(key, new Text(out));
		}
		
	}
	
	public int run (String [] args) throws Exception
	{
		Configuration conf = getConf();
		
		Job job =  new Job(conf, "reverse_index");
		job.setJarByClass(Reverse_index.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.waitForCompletion(true);
		
		return job.isSuccessful() ? 1:0;
		
	}
	
	public static void main (String[] args) throws Exception
	{
		int res = ToolRunner.run(new Configuration(), new Reverse_index(), args);
		System.exit(res);
	}

}

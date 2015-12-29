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


public class Route_filter extends Configured implements Tool{
	
	public static String ct = null;
	public static class Map extends Mapper<LongWritable, Text, NullWritable, Text>
	{
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
		{
			String line = value.toString();
			
			try
			{
				String [] lineSplit = line.split(" ");
				String month = lineSplit[0];
				String day = lineSplit[1];
				String mac = lineSplit[6];
				
				Text out = new Text(month + " " + day + " " + mac);
				
				context.write(NullWritable.get(), out);
			}
			catch(java.lang.ArrayIndexOutOfBoundsException e)
			{
				context.getCounter("Error_counter", ct).increment(1);
				return;
			}
		}
	
	}
	
	public int run (String [] args) throws Exception
	{
		Configuration conf = getConf();
		
		Job job =  new Job(conf, "Route_filter");
		job.setJarByClass(Route_filter.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(Map.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		job.waitForCompletion(true);
		
		return job.isSuccessful() ? 1:0;
		
	}
	
	public static void main (String[] args) throws Exception
	{
		int res = ToolRunner.run(new Configuration(), new Route_filter(), args);
		System.exit(res);
	}

}

public class ServerMoniter implements Watcher,Runnable{

	private ZooKeeper zookeeper;
	private String connectString;
	private int sessionTimeout;
	private String hadoopHome;
	private String mapredJobTracker;

	//Load the init file, configure server's connection
	public void initConf() throws Exception{

		InitConfReader reader = new InitConfReader();
		List<String> keys = new ArrayList<String>();

		keys.add("connectString");
		keys.add("sessionTimeout");
		keys.add("hadoopHome");
		keys.add("mapredJobTracker");

		Map<String,String> confs = reader.getConf(keys);

		this.connectString = confs.get("connectString");
		this.sessionTimeout = confs.get("sessionTimeout");
		this.hadoopHome = confs.get("hadoopHome");
		this.mapredJobTracker = confs.get("mapredJobTracker");

		zookeeper = new ZooKeeper(connectString,sessionTimeout,(Watcher) this);


	}

	//Watch the changement of the tasks nodes

	public ServerMoniter() throws Exception{

		SchedulingServer schedulingserver = new SchedulingServer();
		schedulingserver.intConf();
		schedulingserver.initServer();
		initConf();
	} 


	public void process(WatchedEvent event){


	}

	public void moniterNode() throws Exception{

		List<String> waits = zookeeper.getChildren("/root/client/wait",false);

		if(!waits.isEmpty()){
			JobConf conf = new JobConf();
			conf.set("mapred.job.tracker",mapredJobTracker);
			JobClient jobClient = new JobClient(conf);

			for(String wait:waits){


				String data = new String(zookeeper.getData("/root/client/wait"+wait,false,null));
				JobID jobid = null;

				try{
					jobid = JobID.forName(wait);

				}catch(Exception e)
				{
					System.out.println("job id is wrong");
					Stat stat = zookeeper.exists("/root/client/error"+wait,false);
					if(stat != null)
					{
						zookeeper.delete("/root/client/wait"+wait,-1);
					}
					zookeeper.delete("/root/client/wait"+wait,-1);
					zookeeper.create("/root/client/error"+wait,data.getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
					continue;
				}

			

		

				//Use JobID to check the job's state
				int runStat = jobClient.getJob((org.apache.hadoop.mapred.JobID) jobid).getJobState();

				switch(runStat){

					case JobStatus.RUNNING:
					case JobStatus.PREP:break;
					case JobStatus.SUCCEEDED:
						zookeeper.delete("/root/client/wait"+wait,-1);
						zookeeper.create("/root/client/processed/"+wait,data.getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
						List<String> tempNodes = zookeeper.getData("/root/client/temp",false);
						if(tempNodes == null || tempNodes.size() == 0)
						{
							break;
						}
						else{
							for (String tempNode:tempNodes)
							{
								if(new String(zookeeper.getData("/root/client/temp"+tempNode,false,null)).equals(data))
								{
									zookeeper.delete("/root/client/temp"+tempNode,-1);
								}
							}
						}
						break;
					case JobStatus.FAILED:
					case JobStatus.KILLED:
						zookeeper.delete("/root/client/wait"+wait,-1);
						tempNodes = zookeeper.getChildren("/root/client/temp",false);
						zookeeper.create("/root/client/temp/"+wait，data.getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
						if(tempNodes == null || tempNodes.size() == 0)
						{
							ShellTool.callBack(data,hadoopHome);
						}
						else{
							boolean flag =true;
							for(String tempNode:tempNodes){
								if(new String(zookeeper.getData("/root/client/temp"+tempNode,false,null)).equals(data))
								{
									zookeeper.create("/root/client/error/"+wait,data.getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
									zookeeper.delete("/root/client/temp"+wait,-1);
									zookeeper.delete("/root/client/temp"+tempNode,-1);
									flag =false;
								}
							}
						}

						if(flag){

							ShellTool.callBack(data,hadoopHome);
						}
						break;
					default:
						break;

				}
			}
		}
		

	}

	public void run()
	{
		try{
			ServerMoniter serverWaitMoniter = new ServerMoniter();
			while(true)
			{
				serverWaitMoniter.moniterNode();
				Thread.sleep(5000);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception{
		Thread thread = new Thread(new ServerMoniter());
		thread.start();

	}  


}





















public class SchedulingServer implements Watcher{

	private ZooKeeper zookeeper;
	private String connectString;
	private int sessionTimeout;

	public void intConf() throws Exception{

		InitConfReader reader = new InitConfReader();
		List<String> keys = new ArrayList<String>();

		keys.add("connectString");
		keys.add("sessionTimeout");

		Map<String,String> confs = reader.getConfs(keys);
		this.connectString = confs.get("connectString");
		this.sessionTimeout = confs.get("sessionTimeout");

		zookeeper = new ZooKeeper(connectString,sessionTimeout,this);

	}


	public void initServer() throws Exception{

		Stat stat = zookeeper.exists("/root",false);

		if(stat == null)
		{
			zookeeper.create("/root",null,Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			zookeeper.create("/root/error",null,Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			zookeeper.create("/root/processed",null,Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			zookeeper.create("/root/wait",null,Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			zookeeper.create("/root/temp",null,Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		}

		stat = zookeeper.exists("/root/wait", false);
		if(stat == null)
		{
			zookeeper.create("/root/wait",null,Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		}

		stat = zookeeper.exists("/root/processed", false);
		if(stat == null)
		{
			zookeeper.create("/root/processed",null,Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		}

		stat = zookeeper.exists("/root/error", false);
		if(stat == null)
		{
			zookeeper.create("/root/error",null,Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		}

		stat = zookeeper.exists("/root/temp", false);
		if(stat == null)
		{
			zookeeper.create("/root/temp",null,Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		}
	}

}
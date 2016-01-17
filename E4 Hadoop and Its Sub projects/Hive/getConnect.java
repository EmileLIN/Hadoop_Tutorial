
// Use singleton pattern

public class  getConnect{

	private static Connection conn = null;
	private static Connenction conntomysql =null;

	private getConnect()
	{


	}	

	//Connect Hive
	public static Connection getHiveConn() throws SQLException{
		if(conn =null)
		{
			try{
				Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver"); // Define what drive to use
			}catch(ClassNotFoundException e){
					e.printStackTrace();
					System.exit(1);
			}
		
			//Hive server's default port is 10000
			conn = DriveManager.getConnection("jdbc:hive://master:10000/default","","");  // Host-port, user, password
		}
		return conn;
	}

	public static Connection getMysqlConn() throws SQLException{
		if(conntomysql == null)
		{
			try{
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e)
			{
				e.printStackTrace();
				System.exit(1);
			}

			conn = DriverManager.getConnection("jdbc:mysql://archer:3306/hive?useUnicode=true&characterEncoding=GBK","hadoop","12345678");

		}
		return connmysql;

	}

	//close sessions 

	public static void clostHive() throws SQLException{
		if(conn != null)
		{
			conn.close();
		}
	}

	public static void closeMysql() throws SQLException{
		if(conntomysql != null)
		{
			conntomysql.close();
		}
	}

}
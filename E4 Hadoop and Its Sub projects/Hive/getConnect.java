package com.cstore.transToHive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;






public class getConnect {
	private static Connection conn = null;
	private static Connection conntomysql =null;

	private getConnect()
	{


	}	

	//Connect Hive
	public static Connection getHiveConn() throws SQLException{
		if(conn ==null)
		{
			try{
				Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver"); // Define what drive to use
			}catch(ClassNotFoundException e){
					e.printStackTrace();
					System.exit(1);
			}
		
			//Hive server's default port is 10000
			conn = DriverManager.getConnection("jdbc:hive://192.168.136.104:10000/default", "", "");// Host-port, user, password
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

			conntomysql = DriverManager.getConnection("jdbc:mysql://192.168.136.105:3306/hive?useUnicode=true&characterEncoding=GBK&useSSL=false","hadoop","12345678");

		}
		return conntomysql;

	}

	//close sessions 

	public static void closeHive() throws SQLException{
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

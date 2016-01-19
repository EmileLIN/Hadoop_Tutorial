package com.cstore.transToHive;

import java.sql.ResultSet;
import java.sql.SQLException;

public class exeHiveQL {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		if(args.length<2)
		{
			System.out.print("Please input your conditions:loglevel,date");
			System.exit(1);
		}

		String type =args[0];
		String date =args[1];

		//Create table in Hive
		hiveUtil.createTable("create table if not exists loginfo11(rdate STRING, time ARRAY<string>, type STRING, relateclass STRING, infomation1 STRING, information2 STRING, information3 STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ' COLLECTION ITEMS TERMINATED BY ':' MAP KEYS TERMINATED BY ',' ");

		//load data
		hiveUtil.loadData("load data local inpath '/home/hadoop/Documents/hadooplogfile.txt' overwrite into table loginfo11 ");

		//Filter the result
		ResultSet res1 = hiveUtil.queryHive("select rdate,time[0],type,relateclass,infomation1,information2,information3 from loginfo11 where type = '"+type+"' AND rdate= '"+date+"'");

		//Save the result to mysql
		hiveUtil.hiveTomysql(res1);
		
		//close
		getConnect.closeHive();
		getConnect.closeMysql();


		//Print
		System.out.println("Completed");
	}

}

public class exeHiveQL{

	public static void main(String[] args) throws SQLException{
		if(args.length<2)
		{
			System.out.print("Please input your conditions:loglevel,date");
			System.exit(1);
		}

		String type =args[0];
		String date =args[1];

		//Create table in Hive
		hiveUtil.createTable("create table if not exists loginfo11(rdate String, time ARRAY{string}, type STRING, relateclass STRING, infomation1 STRING, information2 STRING, information3 STRING) ROW FORMAT DELIMITED FIELDS BY ' ' COLLECTION ITEMS TERMINATED BY ',' MAP KEYS TERMINATED BY ':' ");

		//load data
		hiveUtil.loadData("load data local inpath '/home/hadoop/dataset/testlog/*' overwrite into table loginfo11 ");

		//Filter the result
		ResultSet res1 = hiveUtil.queryHive("select rdate,time[0],type,relateclass,infomation1,information2,information3 from loginfo11 where type = '"+type+"' AND time= '"+date+"'");

		//Save the result to mysql
		hiveUtil.hiveTomysql(res1);
		
		//close
		getConnect.closeHive();
		getConnect.closeMsyql();


		//Print
		System.out.println("Completed");
	}

}
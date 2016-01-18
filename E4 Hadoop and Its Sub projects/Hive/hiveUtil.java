public class hiveUtil{
	public static void createTable(String hiveql) throws SQLException{
		Connection con = getConnect.getHiveConn();
		Statement stmt = con.createStatement();
		stmt.executeQuery(hiveql);
	}

	public static ResultSet queryHive(String hiveql) throws SQLException{
		Connection con = getConnect.getHiveConn();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(hiveql);
		return res;
	}

	public static void loadData(String hiveql) throws SQLException{
		Connection con = getConnect.getHiveConn();
		Statement stmt = con.createStatement();
		stmt.executeQuery(hiveql);
	}

	public static void hiveTomysql(ResultSet hiveres) throws SQLException{
		Connection con = getConnect.getMysqlConn();
		Statement stmt = con.createStatement();
		while(hiveres.next()){
			String rdate=hiveres.getString(1);
			String time=hiveres.getString(2);
			String type=hiveres.getString(3);
			String relateclass=hiveres.getString(4);
			String information=hiveres.getString(5)+hiveres.getString(6)+hiveres.getString(7);
			stmt.executeUpdate("insert into hadooplog values(0,'"+rdate+"','"+time+"','"+type+"','"+relateclass+"','"+information+"')");

		}
	}


}
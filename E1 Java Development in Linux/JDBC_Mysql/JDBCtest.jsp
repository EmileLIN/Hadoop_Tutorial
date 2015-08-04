<%@ page import = "java.sql.*" %>

<%
	String url = "jdbc:mysql://localhost:3306/testbase";
	String usr = "root";
	String password = "xxxxxxxxxxxxxx";

	try{
		Class.forName("com.mysql.jdbc.Driver");
	}catch(ClassNotFoundException e)
	{
		out.println("Can not find the jdbc driver");
	}

	try{
		Connection conn = DriverManager.getConnection(url,usr,password);
		Statement stat = conn.createStatement();

		String sql1 = "INSERT INTO testTable VALUES(1,'abc')";
		stat.executeUpdate(sql1);

		String sql2 = "SELECT * FROM testTable";
		ResultSet rs = stat.executeQuery(sql2);
		while(rs.next())
		{
			String a = rs.getString(1);
			String b = rs.getString(2);
		
			out.println(a);
			out.println(b);
		}
		
	}catch(SQLException e)
	{
		out.println("Can not reach the database we want");
	}
%>

<%@ page import = "java.sql.*" %>

<body>
	<%
		//Recieve Parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//Connect the database
		String url = "jdbc:mysql://localhost:3306/testbase";
		String usr = "root";
		String pass = "courageliner";

		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			out.println("Can not find the jdbc driver");
		}

		try{
			Connection conn = DriverManager.getConnection(url,usr,pass);
			Statement stat = conn.createStatement();

			String sql2 = "SELECT * FROM users WHERE username = '"+username+"'";
			out.println(sql2);
			ResultSet rs = stat.executeQuery(sql2);

			if(rs.next())
			{
				String passw = rs.getString(2);
				if(passw.equals(password))
				{
					%>
					<jsp:forward page= "login_success.jsp"/>
					<%						
				}	
				else
				{
					%>
					<jsp:forward page= "login_fail.jsp"/>
					<%
				}
				
			}
			else
			{
			%>
				<jsp:forward page= "login_fail.jsp"/>	
			<%
			}
		
		}catch(SQLException e)
		{
			out.println("Can not reach the database we want");
		}

		
	%>
</body>

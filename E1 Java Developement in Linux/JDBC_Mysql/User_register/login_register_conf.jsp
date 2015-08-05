<%@ page import = "java.sql.*" %>

<body>
	<%
		//Recieve Parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String con_password = request.getParameter("password_confirm");

		if(password.equals(con_password))
		{
		
			//Connect the database
			String url = "jdbc:mysql://localhost:3306/testbase";
			String usr = "root";
			String pass = "xxxxxxxxxx";

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
				ResultSet rs = stat.executeQuery(sql2);

				if(rs.next())
				{			
					
					out.println("The user already exists");
																
				}
				else
				{
					String sql = "INSERT INTO users VALUES('"+username+"','"+password+"')";
					stat.executeUpdate(sql);
					out.println("Successfully registered");
				}
		
			}catch(SQLException e)
			{
				out.println("Can not reach the database we want");
			}
		}else
		{
			out.println("Password is not the same with confirm password");
		}
		
	%>
</body>

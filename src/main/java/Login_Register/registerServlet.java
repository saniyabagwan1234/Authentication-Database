package Login_Register;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class registerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		String Firstname,address,Email,phoneNo,url,user,pass;
		String password ;

		Firstname=req.getParameter("firstname");
		phoneNo=req.getParameter("phone");
		address=req.getParameter("add");
		Email=req.getParameter("email");
		password=req.getParameter("password");
		
		PrintWriter out=resp.getWriter();
		
		//out.print("Register Successfully"+"<br>");
		out.print("Welcome " + Firstname + "<br>");
		out.print("Name: "+Firstname+"<br>");
		out.print("Phone No.: "+phoneNo+"<br>");		
		out.print("Address: "+address+"<br>");
		out.print("Email : "+Email);	
		
		out.print("<form action='Home.html'>");
		out.print("<button type='Back' value='Back'>Back</button>");
	    out.print("</form>");
				
	    try {
			url ="jdbc:mysql://localhost:3306/login_table";
			user="root";
			pass="root";
					
			Class.forName("com.mysql.cj.jdbc.Driver");

	Connection con = DriverManager.getConnection(url,user,pass);
	PreparedStatement ps=con.prepareStatement("insert into registerServlet(firstname,phoneNo,address,email,password)values(?,?,?,?,?)");
	
	ps.setString(1,Firstname);
	ps.setString(2, phoneNo);
	ps.setString(3, address);
	ps.setString(4, Email);
	ps.setString(5,password);
	
int i=ps.executeUpdate();
if (i>0) {
	out.print("Register Success...!");
}else {
	out.print("Register failed");
}

out.print("Welcome " + Firstname + "<br>");
out.print("Name: "+Firstname+"<br>");
out.print("Phone No.: "+phoneNo+"<br>");		
out.print("Address: "+address+"<br>");
out.print("Email : "+Email);	

out.print("<form action='Home.html'>");
out.print("<button type='Back' value='Back'>Back</button>");
out.print("</form>");
		} catch (Exception e) {	
			System.out.println(e);
		}	
}
}

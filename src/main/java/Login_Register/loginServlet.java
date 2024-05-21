package Login_Register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class loginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String url, user, pass, query, email_id, newpassword;
		email_id = req.getParameter("email");
		newpassword = req.getParameter("password");

		out.print("<form action='Home.html'>");
		out.print("<button type='Back' value='Back'>Back</button>");
		out.print("</form>");

		try {
			url = "jdbc:mysql://localhost:3306/login_table";
			user = "root";
			pass = "root";

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, user, pass);

			Statement st = con.createStatement();

			String Query = "select password from registerServlet where email=?";
			PreparedStatement ps = con.prepareStatement(Query);
			ps.setString(1, email_id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String NPassword = rs.getString("Password");
				if (newpassword.equals(NPassword)) {
					out.println("Login Successful..");
				} else {
					out.println("Incorrect Password..");
				}
			} else {
				out.println("User not found..");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

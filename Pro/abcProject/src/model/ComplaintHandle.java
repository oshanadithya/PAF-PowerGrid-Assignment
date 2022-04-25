package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ComplaintHandle {
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/powergridcomp",
			"root", "oshan@0765649523");
			//For testing
			System.out.print("Successfully connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertComplaint(String name, String email, String contact, String complaint)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}
			
			// create a prepared statement
			String query = " insert into  (`idcomplaints`,`name`,`email`,`contact`,'complaint')"
			+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, contact);
			preparedStmt.setString(5, complaint);
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
	return output;
	}

	public String readComplaints() {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Name</th>" +
			"<th>Email</th>" +
			"<th>Contact</th>" +
			"<th>Complaint</th><th>Remove</th></tr>";
			String query = "select * from complaints";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String ComplaintID = Integer.toString(rs.getInt("idcomplaints"));
				String Name = rs.getString("name");
				String Email = rs.getString("email");
				String Contact = Double.toString(rs.getDouble("contact"));
				String Complaint = rs.getString("complaint");
				
				// Add into the html table
				output += "<tr><td>" + Name + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Contact + "</td>";
				output += "<td>" + Complaint + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				+ "<td><form method='post' action='items.jsp'>"
				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				+ "<input name='itemID' type='hidden' value='" + ComplaintID
				+ "'>" + "</form></td></tr>";
			}
			con.close();
			
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return null;
	}
}

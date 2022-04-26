package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String First_Name, String Last_Name, String Address, String Account_No, String Contact_No, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user(`UserID`,`First_Name`,`Last_Name`,`Address`,`Account_No`,`Contact_No`,`Email`)" + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, First_Name);
			preparedStmt.setString(3, Last_Name);
			preparedStmt.setString(4, Address);
			preparedStmt.setString(5, Account_No);
			preparedStmt.setString(6, Contact_No);
			preparedStmt.setString(7, Email);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User ID</th><th>First Name</th><th>Last Name</th><th>Address</th><th>Account No</th><th>User Contact No</th><th>Email</th></tr>";
			String query = "select * from user";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String UserID = Integer.toString(rs.getInt("UserID"));
				String First_Name = rs.getString("First_Name");
				String Last_Name = rs.getString("Last_Name");
				String Address = rs.getString("Address");
				String Account_No = rs.getString("Account_No");
				String Contact_No = rs.getString("Contact_No");
				String Email = rs.getString("Email");

				output += "<tr><td>" + UserID + "</td>";
				output += "<td>" + First_Name + "</td>";
				output += "<td>" + Last_Name + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + Account_No + "</td>";
				output += "<td>" + Contact_No + "</td>";
				output += "<td>" + Email + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String UserID, String First_Name, String Last_Name, String Address, String Account_No, String Contact_No, String Email) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE user SET First_Name=?,Last_Name=?,Address=?,Account_No=?,Contact_No=?,Email=? WHERE UserID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, First_Name);
			preparedStmt.setString(2, Last_Name);
			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, Account_No);
			preparedStmt.setString(5, Contact_No);
			preparedStmt.setString(6, Email);
			preparedStmt.setInt(7, Integer.parseInt(UserID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(String UserID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from user where UserID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(UserID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}

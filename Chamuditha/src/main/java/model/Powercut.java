package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Powercut {
	
	public Connection connect() {
		Connection con = null;

		try {
			
			String url = String.format("jdbc:mysql://127.0.0.1:3306/powercut_schedule");
			String username = "root";
			String password = "root";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	//create method
		public String insertPowercut(String id, String name, String group, String dayStartTime, String dayEndTime, String nightStartTime, String nightEndTime) {
			Connection con = connect();
			String output = "";
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into powercutschedule (`id`,`name`,`group`,`dayStartTime`,`dayEndTime`,`nightStartTime`,`nightEndTime`)"
							+ "values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt;
			try {
				preparedStmt = con.prepareStatement(query);

				preparedStmt.setString(1, id);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, group);
				preparedStmt.setString(4, dayStartTime);
				preparedStmt.setString(5, dayEndTime);
				preparedStmt.setString(6, nightStartTime);
				preparedStmt.setString(7, nightEndTime);

				preparedStmt.execute();
				con.close();
				output = "added successfully";
			} catch (SQLException e) {
				output = "Error while adding";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//retrieve method
		public String readPowercuts() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading Customers.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>ID</th><th>Name</th><th>Group</th><th>day Start Time</th>"
						+ "<th>day End Time</th><th>night Start Time</th><th>night End Time</th>";

				String query = "select * from powercutschedule";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String id = rs.getString("id");
					String name = rs.getString("name");
					String group = rs.getString("group");
					String dayStartTime = rs.getString("dayStartTime");
					String dayEndTime = rs.getString("dayEndTime");
					String nightStartTime = rs.getString("nightStartTime");
					String nightEndTime = rs.getString("nightEndTime");

					// Add into the html table
					output += "<tr><td>" + id + "</td>";
					output += "<td>" + name + "</td>";
					output += "<td>" + group + "</td>";
					output += "<td>" + dayStartTime + "</td>";
					output += "<td>" + dayEndTime + "</td>";
					output += "<td>" + nightStartTime + "</td>";
					output += "<td>" + nightEndTime + "</td>";
				}
				con.close();

				output += "</table>";
			}

			catch (Exception e) {
				output = "Error while reading the powercut records.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//update method
		public String updatePowercut(String ID,String name, String group, String dayStartTime, String dayEndTime, String nightStartTime, String nightEndTime)

		{
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement

				String query = "UPDATE powercutschedule SET name=?, group=?, dayStartTime=?,"
						+ " dayEndTime=?, nightStartTime=?,"
						+ " nightEndTime=? WHERE id=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, group);
				preparedStmt.setString(3, dayStartTime);
				preparedStmt.setString(4, dayEndTime);
				preparedStmt.setString(5, nightStartTime);
				preparedStmt.setString(6, nightEndTime);

				preparedStmt.setString(7,ID);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the powercut Record.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//delete method
		public String deletePowercut(String id) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from powercutschedule where id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1,id);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the Powercut Record.";
				System.err.println(e.getMessage());
			}
			return output;
		}

}

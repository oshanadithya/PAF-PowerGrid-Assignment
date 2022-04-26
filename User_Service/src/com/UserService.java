package com;

import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class UserService {
	User UserObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return UserObj.readUser();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
			@FormParam("First_Name") String First_Name,
			@FormParam("Last_Name") String Last_Name,
			@FormParam("Address") String Address,
			@FormParam("Account_No") String Account_No,
			@FormParam("Contact_No") String Contact_No,
			@FormParam("Email") String Email) {
		String output = UserObj.insertUser(First_Name, Last_Name, Address, Account_No, Contact_No, Email);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateUser(String userData) {
		
		JsonObject UserObject = new JsonParser().parse(userData).getAsJsonObject();
		
		String UserID = UserObject.get("UserID").getAsString();
		String First_Name = UserObject.get("First_Name").getAsString();
		String Last_Name = UserObject.get("Last_Name").getAsString();
		String Address = UserObject.get("Address").getAsString();
		String Account_No = UserObject.get("Account_No").getAsString();
		String Contact_No = UserObject.get("Contact_No").getAsString();
		String Email = UserObject.get("Email").getAsString();
		
		String output = UserObj.updateUser(UserID, First_Name, Last_Name, Address, Account_No, Contact_No, Email);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData) {
		
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		String UserID = doc.select("UserID").text();
		String output = UserObj.deleteUser(UserID);
		return output;
		
	}
}

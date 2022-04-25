package com;

import model.ComplaintHandle;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/complaints")
public class createComplaint {
	
	ComplaintHandle compObj = new ComplaintHandle();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readComplaints() {
		return compObj.readComplaints();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertComplaint(@FormParam("name")String name, @FormParam("email")String email, @FormParam("contact")String contact, @FormParam("complaint")String complaint)	{
		
		String output = compObj.insertComplaints(name, email, contact, complaint);
		
		return output ;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateComplaints (String compData) {
		
		//Convert the input string to a JSON object
		JsonObject compObject = new JsonParser().parse(compData).getAsJsonObject();
		
		//Read the values from the JSON object
		String idcomplaints = compObject.get("idcomplaints").getAsString();
		String name = compObject.get("name").getAsString();
		String email = compObject.get("email").getAsString();
		String contact = compObject.get("contact").getAsString();
		String complaint = compObject.get("complaint").getAsString();
		
		String output = compObj.updateComplaints(idcomplaints, name, email, contact, complaint);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String compData) {
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(compData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String idcomplaints = doc.select("idcomplaints").text();
		
		String output = compObj.deleteComplaints(idcomplaints);
		
		return output;
		
	}

}
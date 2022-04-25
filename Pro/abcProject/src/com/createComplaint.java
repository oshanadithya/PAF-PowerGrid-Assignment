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
	public String readComplaints()
	{
		return compObj.readComplaints();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertComplaint(@FormParam("name")String name, @FormParam("email")String email, @FormParam("contact")String contact, @FormParam("complaint")String complaint)	{
		
		String output = compObj.insertComplaint(name, email, contact, complaint);
		return output ;
	}

}
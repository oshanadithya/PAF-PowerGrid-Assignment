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

}

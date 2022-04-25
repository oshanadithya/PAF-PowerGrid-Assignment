package com;

import model.Units;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/Items")

public class UnitService {
	
	Units unitObj = new Units();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUnits()
	{
	return unitObj.readUnits();
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUnits(@FormParam("unitcode") String unitcode, 
	 @FormParam("name") String name, 
	 @FormParam("address") String address, 
	 @FormParam("date") String date,
	 @FormParam("nunits") String nunits,
	 @FormParam("period") String period,
	 @FormParam("pricep") String pricep,
	 @FormParam("tprice") String tprice
	 ) 
	{ 
		String output = unitObj.insertUnits(unitcode, name, address, date,nunits,period,pricep,tprice ); 
	 	return output; 
	}
	
 
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUnits(String unitData)
	{
	//Convert the input string to a JSON object
		
		JsonObject unitObject = new JsonParser().parse(unitData).getAsJsonObject();
	//Read the values from the JSON object
	String uid =  unitObject.get("uid").getAsString();
	String unitcode = unitObject.get("unitcode").getAsString();
	String name = unitObject.get("name").getAsString();
	String address = unitObject.get("address").getAsString();
	String date = unitObject.get("date").getAsString();
	String nunits = unitObject.get("nunits").getAsString();
	String period = unitObject.get("period").getAsString();
	String pricep = unitObject.get("pricep").getAsString();
	String tprice = unitObject.get("tprice").getAsString();
	
	String output = unitObj.updateUnits(uid, unitcode, name, address, date,nunits,period, pricep, tprice);
	
	return output;
	}
	

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUnits(String unitData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(unitData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String uid = doc.select("uid").text();
	String output = unitObj.deleteUnits(uid);
	return output;
	}
	
}

package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Powercut;

@Path("/powercut")
public class PowercutService {
	
	Powercut powercutObj = new Powercut();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPowercuts() {
		return powercutObj.readPowercuts();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPowercut(@FormParam("id") String id,
			@FormParam("name") String name, @FormParam("group") String group,
			@FormParam("dayStartTime") String dayStartTime ,@FormParam("dayEndTime") String dayEndTime,
			@FormParam("nightStartTime") String nightStartTime, @FormParam("nightEndTime") String nightEndTime) {
		String output = powercutObj.insertPowercut(id,name,group,dayStartTime,dayEndTime,nightStartTime,nightEndTime);
		return output;
	}
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePowercut(String powercutData) {
		// Convert the input string to a JSON object
		JsonObject powercutObject = new JsonParser().parse(powercutData).getAsJsonObject();
		// Read the values from the JSON object
		String id = powercutObject.get("id").getAsString();
		String name = powercutObject.get("name").getAsString();
		String group = powercutObject.get("group").getAsString();
		String dayStartTime = powercutObject.get("dayStartTime").getAsString();
		String dayEndTime = powercutObject.get("dayEndTime").getAsString();
		String nightStartTime = powercutObject.get("nightStartTime").getAsString();
		String nightEndTime = powercutObject.get("nightEndTime").getAsString();
		String output = powercutObj.updatePowercut( id,name, group, dayStartTime, dayEndTime, nightStartTime,
				                                   nightEndTime );
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePowercut(String powercutData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(powercutData, "", Parser.xmlParser());

		// Read the value from the element <id>
		String id = doc.select("id").text();
		String output = powercutObj.deletePowercut(id);
		return output;
	}
	
}

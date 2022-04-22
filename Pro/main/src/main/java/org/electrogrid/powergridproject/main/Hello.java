package org.electrogrid.powergridproject.main;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/Hello") 
public class Hello {
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello World";
	}

}
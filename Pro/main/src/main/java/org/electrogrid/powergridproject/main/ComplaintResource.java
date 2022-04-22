package org.electrogrid.powergridproject.main;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("complaint")
public class ComplaintResource {
	
	@GET
    //@Produces(MediaType.APPLICATION_XML)
    public Complaint getComplaint() {
		
		System.out.println("getComplaint called!");
		
		Complaint c1 = new Complaint();
		
		c1.setName("Oshan");
		c1.setEmail("oshan.w@gmail.com");
		c1.setContact("0765649523");
		c1.setComplaint("Sample complaint");
		
        return c1;
    }
}

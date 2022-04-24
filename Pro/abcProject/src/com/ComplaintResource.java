package com;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("complaints")
public class ComplaintResource {
	
	/*@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_XML)
	public List<Complaint> getComplaint() {
		
		System.out.println("getComplaint called...");
		
		Complaint c1 = new Complaint();
		c1.setName("Oshan");
		c1.setEmail("o@gmail.com");
		c1.setContact("0765649523");
		c1.setComplaint("Sample Complaint");
		
		Complaint c2 = new Complaint();
		c2.setName("Ashan");
		c2.setEmail("a@gmail.com");
		c2.setContact("0765642323");
		c2.setComplaint("Sample Complaint2");
		
		List<Complaint> complaints = Arrays.asList(c1,c2);
		
		return complaints;*/
	
	ComplaintRepository repo = new ComplaintRepository();
	
	@GET
	@Produces (MediaType.APPLICATION_XML)
	public List<Complaint> getComplaints() {
		System.out.println("getComplaint called...");
		return repo.getComplaints();
		
	
	}
	
	@POST
	@Path("comp")
	public Complaint createComplaint(Complaint c1) {
		
		System.out.println(c1);
		repo.create(c1);
		return c1;
	}
}

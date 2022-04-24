package com;

import java.util.ArrayList;
import java.util.List;

public class ComplaintRepository {
	
	List<Complaint> complaints;
	
	public ComplaintRepository() {
		complaints = new ArrayList<>();
		
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
		
		complaints.add(c1);
		complaints.add(c2);
	}
	
	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void create(Complaint c1) {
		// TODO Auto-generated method stub
		complaints.add(c1);
	}
	
	/*public Complaint getComplaint() {
		
	}*/
	
}

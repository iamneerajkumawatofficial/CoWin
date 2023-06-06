package com.neerajkumawatProjects.CoWin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoWin {
	@GetMapping("/api/v2/admin/location/states")
	public Map States() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection
			        ("jdbc:mysql://localhost:3306/project","root","0230");
			Statement stmt=con.createStatement();
			String query="select * from states";
			ResultSet rs = stmt.executeQuery(query);
			List l=new ArrayList();
			while(rs.next()) {
				HashMap map = new HashMap();
				map.put("state_id",rs.getString("state_id"));
				map.put("state_name",rs.getString("state_name"));
				l.add(map);
			}
			Map data =new HashMap();
			data.put("states",l);
			data.put("ttl", 24);
			return data;
		}  catch(Exception ex) {
			
		}
		return null;
	}
	@GetMapping("/api/v2/admin/location/districts/{stateId}")
	public Map district(@PathVariable("stateId")String state_id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection
			        ("jdbc:mysql://localhost:3306/project","root","0230");
			Statement stmt=con.createStatement();
			PreparedStatement stmt1 = con.prepareStatement("select * from project.district where state_id=?");
			stmt1.setInt(1,Integer.parseInt(state_id));
			ResultSet rs = stmt1.executeQuery();
			List l=new ArrayList();
			while(rs.next()) {
				HashMap map = new HashMap();
				map.put("district_id",rs.getString("district_id"));
				map.put("district_name",rs.getString("district_name"));
				l.add(map);
			}
			Map data =new HashMap();
			data.put("districts",l);
			data.put("ttl", 24);
			return data;
		}  catch(Exception ex) {
			
		}
		return null;
	}
	@GetMapping("/api/v2/admin/sessions/findByPin/{pincode}")
	public Map Dem(@PathVariable("pincode") String pincode)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection
					("jdbc:mysql://localhost:3306/project", "root","0230");
			    PreparedStatement stmt = con.prepareStatement("select * from find_by_pin where pincode =?");
			    stmt.setString(1, pincode);
			    ResultSet rs=stmt.executeQuery();
			    List l=new ArrayList();
			    while(rs.next())
			    {
			    	Map map =new HashMap();
			    	map.put("PINCODE",rs.getString("PINCODE"));
			    	map.put("Center_id", rs.getString("Center_id"));
			    	map.put("Center_Address",rs.getString("Center_Address"));
			    	map.put("District_id",rs.getString("District_id"));
			    	map.put("State_name",rs.getString("State_name"));
			    	map.put("latitude",rs.getString("latitude"));
			    	map.put("longtitude",rs.getString("longitude"));
//			    	map.put("Fee_Type",rs.getString("Fee_Type"));
//			    	map.put("fee",rs.getString("fee"));
//			    	map.put("date",rs.getString("date"));
//			    	map.put("Available_capacity",rs.getString("Available_capacity"));
//			    	map.put("Available_capacity_dose1",rs.getString("Available_capacity_dose1"));
//			    	map.put("Available_capacity_dose2",rs.getString("Available_capacity_dose2"));
//			    	map.put("Age_limit",rs.getString("Age_limit"));
//			    	map.put("Vaccine_name",rs.getString("Vaccine_name"));
			    	l.add(map);
			    }
			    Map data = new HashMap();
			    data.put("pincode" , l);
			    return data;
			    
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/api/v2/appointment/sessions/public/findByDistrict")
	public Map findByDistrict (String district_id ,String date) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection
			        ("jdbc:mysql://localhost:3306/project","root","0230");
			Statement stmt=con.createStatement();
			PreparedStatement stmt1 = con.prepareStatement("Select * from project.slots_available a join district" +
			"on a.district_id=b.district_id join states c on" + "b.state_id=c.state_id where a.district_id=? and date=?");
			stmt1.setInt(1,Integer.parseInt(district_id));
			stmt1.setDate(2, null);
			ResultSet rs = stmt1.executeQuery();
			List l=new ArrayList();
			while(rs.next()) {
				HashMap map = new HashMap();
				map.put("district_id",rs.getString("district_id"));
				map.put("district_name",rs.getString("district_name"));
				l.add(map);
			}
			Map data =new HashMap();
			data.put("districts",l);
			data.put("ttl", 24);
			return data;
		}  catch(Exception ex) {
			
		}
		return null;
	}

}

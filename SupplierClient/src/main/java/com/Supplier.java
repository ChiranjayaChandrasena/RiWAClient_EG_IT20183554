package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Supplier {
	
	  //A common method to connect to the DB 
	  private Connection connect() 
	  { 
	    Connection con = null; 
	 
	    try 
	    { 
	      Class.forName("com.mysql.jdbc.Driver"); 
	       
	      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "database"); 
	    } 
	    catch (Exception e) 
	    {e.printStackTrace();} 
	 
	    return con; 
	  } 
	 
	  //insert supplier
	  public String insertSupplier(String name, String address, String NIC, String phone) 
	  { 
	    String output = ""; 
	 
	    try 
	    { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for inserting."; } 
	 
	      // create a prepared statement 
	      String query = " insert into power_supplier(`powerSupplierID`,`name`,`address`,`NIC`,`phone`)" 
	          + " values (?, ?, ?, ?, ?)"; 
	 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      // binding values 
	      preparedStmt.setInt(1, 0); 
	      preparedStmt.setString(2, name); 
	      preparedStmt.setString(3, address); 
	      preparedStmt.setString(4, NIC); 
	      preparedStmt.setString(5, phone); 
	 
	      // execute the statement 
	      preparedStmt.execute(); 
	      con.close(); 
	 
	      String newSuppliers = readSuppliers(); 
	      output = "{\"status\":\"success\", \"data\": \"" + 
	      newSuppliers + "\"}"; 
	    } 
	    catch (Exception e) 
	    { 
	    	output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			System.err.println(e.getMessage()); 
	    } 
	 
	    return output; 
	  } 
	  
	  
	  //view all suppliers
	  public String readSuppliers() 
	  { 
	    String output = ""; 
	 
	    try 
	    { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for reading."; } 
	 
	      // Prepare the html table to be displayed 
	      output = "<table class='table'><tr><th>Supplier ID</th><th>Supplier Name</th><th>Supplier Address</th>" + 
	        "<th>Supplier NIC</th>" +  
	        "<th>Supplier Phone</th>"+
	        "<th>Update</th><th>Remove</th></tr>";   
	     
	      String query = "select * from power_supplier"; 
	      Statement stmt = con.createStatement(); 
	      ResultSet rs = stmt.executeQuery(query); 
	 
	      // iterate through the rows in the result set 
	      while (rs.next()) 
	      { 
	        String powerSupplierID = Integer.toString(rs.getInt("powerSupplierID")); 
	        String name = rs.getString("name"); 
	        String address = rs.getString("address"); 
	        String NIC = rs.getString("NIC"); 
	        String phone = rs.getString("phone"); 
	 
	        // Add into the html table 
	        output += "<tr><td><input id='hidpowerSupplierIDUpdate' name='hidpowerSupplierIDUpdate' type='hidden' value='" + powerSupplierID + "'>" + powerSupplierID + "</td>"; 
	        output += "<td>" + name + "</td>";
	        output += "<td>" + address + "</td>"; 
	        output += "<td>" + NIC + "</td>"; 
	        output += "<td>" + phone + "</td>"; 
	        
	        // buttons 
	        output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"+ powerSupplierID + "'>" + "</td>"
	        		 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"+ powerSupplierID + "'>" + "</td></tr>"; 
	 
	      } 
	 
	      con.close(); 
	 
	      // Complete the html table 
	      output += "</table>"; 
	    } 
	    catch (Exception e) 
	    { 
	      output = "Error while reading the suppliers."; 
	      System.err.println(e.getMessage()); 
	    } 
	 
	    return output; 
	  } 
	  
	  
	  //view supplier by id
	  public String readSupplierByID(String powerSupplierId)
	  { 
	    String output = ""; 
	 
	    try 
	    { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for reading."; } 
	 
	      // Prepare the html table to be displayed 
	      output = "<table border='1'><tr><th>Supplier ID</th><th>Supplier Name</th><th>Supplier Address</th>" + 
	        "<th>Supplier NIC</th>" +  
	        "<th>Supplier Phone</th></tr>";   
	     
	      String query = "select * from power_supplier where powerSupplierID='"+powerSupplierId+"'"; 
	      Statement stmt = con.createStatement(); 
	      ResultSet rs = stmt.executeQuery(query); 
	 
	      // iterate through the rows in the result set 
	      while (rs.next()) 
	      { 
	        String powerSupplierID = Integer.toString(rs.getInt("powerSupplierID")); 
	        String name = rs.getString("name"); 
	        String address = rs.getString("address"); 
	        String NIC = rs.getString("NIC"); 
	        String phone = rs.getString("phone"); 
	 
	        // Add into the html table 
	        output += "<tr><td>" + powerSupplierID + "</td>"; 
	        output += "<td>" + name + "</td>";
	        output += "<td>" + address + "</td>"; 
	        output += "<td>" + NIC + "</td>"; 
	        output += "<td>" + phone + "</td>"; 
	 
	      } 
	 
	      con.close(); 
	 
	      // Complete the html table 
	      output += "</table>"; 
	    } 
	    catch (Exception e) 
	    { 
	      output = "Error while reading the suppliers."; 
	      System.err.println(e.getMessage()); 
	    } 
	 
	    return output; 
	  }
	  
	 
	  //update suppliers
	  public String updateSupplier(String ID, String name, String address, String NIC, String phone)
	   
	  { 
	    String output = ""; 
	    String decodedmon1 = java.net.URLDecoder.decode(name); 
	    String decodedmon2 = java.net.URLDecoder.decode(address); 
	 
	    try 
	    { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for updating."; } 
	 
	      // create a prepared statement 
	      String query = "UPDATE power_supplier SET name=?,address=?,NIC=?,phone=? WHERE powerSupplierID=?"; 
	 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      // binding values 
	      preparedStmt.setString(1, decodedmon1); 
	      preparedStmt.setString(2, decodedmon2); 
	      preparedStmt.setString(3, NIC); 
	      preparedStmt.setString(4, phone); 
	      preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
	      // execute the statement 
	      preparedStmt.execute(); 
	      con.close(); 
	 
	      String newUsers = readSuppliers(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newUsers + "\"}"; 
	    } 
	    catch (Exception e) 
	    { 
	    	output = "{\"status\":\"error\", \"data\": \"Error while updating the supplier.\"}"; 
			 System.err.println(e.getMessage()); 
	    } 
	 
	    return output; 
	  } 
	 
	  //delete suppliers
	  public String deleteSupplier(String powerSupplierID) 
	  { 
	    String output = ""; 
	 
	    try 
	    { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for deleting."; } 
	 
	      // create a prepared statement 
	      String query = "delete from power_supplier where powerSupplierID=?"; 
	 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      // binding values 
	      preparedStmt.setInt(1, Integer.parseInt(powerSupplierID)); 
	 
	      // execute the statement 
	      preparedStmt.execute(); 
	      con.close(); 
	 
	      String newSuppliers= readSuppliers(); 
	      output = "{\"status\":\"success\", \"data\": \"" +   
	    		  newSuppliers + "\"}";
	    } 
	    catch (Exception e) 
	    { 
	    	output = "{\"status\":\"error\", \"data\": \"Error while deleting the supplier.\"}"; 
	   	    System.err.println(e.getMessage()); 
	    } 
	 
	    return output; 
	  }

}

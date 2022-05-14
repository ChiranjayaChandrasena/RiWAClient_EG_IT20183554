package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SupplierAPI")

	public class SupplierAPI extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		Supplier supplierObj = new Supplier();
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
		
		private static Map getParasMap(HttpServletRequest request) 
		{ 
		 Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
		 String queryString = scanner.hasNext() ? 
		 scanner.useDelimiter("\\A").next() : ""; 
		 scanner.close(); 
		 String[] params = queryString.split("&"); 
		 for (String param : params) 
		 {
			 String[] p = param.split("="); 
			 map.put(p[0], p[1]); 
			 } 
			 } 
			catch (Exception e) 
			 { 
			 } 
			return map; 
			}
		
	    public SupplierAPI() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				 throws ServletException, IOException 
				{ 
					String output = supplierObj.insertSupplier(
					request.getParameter("name"), 
					request.getParameter("address"), 
					request.getParameter("NIC"), 
					request.getParameter("phone"));
					response.getWriter().write(output); 
				}

		/**
		 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
		 */
		protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
			 Map paras = getParasMap(request); 
			 
			 String output = supplierObj.updateSupplier(
			 paras.get("hidItemIDSave").toString(), 
			 paras.get("name").toString(), 
			 paras.get("address").toString(), 
			 paras.get("NIC").toString(), 
			 paras.get("phone").toString()); 
			 response.getWriter().write(output);
		}

		/**
		 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
		 */
		protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			  Map paras = getParasMap(request); 
			 
			  String output = supplierObj.deleteSupplier(paras.get("powerSupplierID").toString()); 
			 
			  response.getWriter().write(output);
		}

	}
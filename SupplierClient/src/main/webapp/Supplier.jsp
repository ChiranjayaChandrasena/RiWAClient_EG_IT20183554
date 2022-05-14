<%@page import="com.Supplier"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (session.getAttribute("Username") == null) {
	response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Supplier Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery.min.js"></script>
<script src="Components/Supplier.js"></script>
<script src="Components/auth.js"></script>
<body>
<div align="right">
	<button id="btnLogout" class='btnRemove btn btn-danger' align="right">LogOut</button>
</div>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Supplier Management</h1>
				<form id="formItem" name="formItem">
					Name: <input id="name" name="name" type="text" class="form-control form-control-sm"> <br> 
					Address: <input id="address" name="address" type="text" class="form-control form-control-sm"> <br> 
					NIC: <input id="NIC" name="NIC" type="text" class="form-control form-control-sm"> <br> 
					Phone: <input id="phone" name="phone" type="text" class="form-control form-control-sm"> <br> 
						<input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
					<%
					Supplier supplierObj = new Supplier();
					out.print(supplierObj.readSuppliers());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
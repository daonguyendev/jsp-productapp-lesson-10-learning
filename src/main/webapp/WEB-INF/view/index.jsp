<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List" %>
<%@ page import="com.daonguyen.model.Product" %>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product List</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h4 class="my-4">Product List</h4>
				<a href="<%= request.getContextPath() %>/product/add" class="btn btn-primary mb-3">Add New Product</a>
			    <table class="table table-bordered">
				    <thead>
				      <tr>
				        <th>Code</th>
				        <th>Name</th>
				        <th>Amount</th>
				        <th>Price</th>
				        <th>#</th>
				      </tr>
				    </thead>
				    <tbody>
				        <% for (Product product : products) {%>
                          <tr>
                            <td><%=product.getCode()%></td>
                            <td><%=product.getName()%></td>
                            <td><%=product.getAmount()%></td>
                            <td><%=product.getPrice()%></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/product/edit?code=<%=product.getCode()%>" class="btn btn-sm btn-info">Edit</a>
                                <a href="<%= request.getContextPath() %>/product/remove?code=<%=product.getCode()%>" class="btn btn-sm btn-danger">Remove</a>
                            </td>
                          </tr>
				      <% } %>
				    </tbody>
			  	</table>
			</div>
		</div>
	</div>
</body>
</html>
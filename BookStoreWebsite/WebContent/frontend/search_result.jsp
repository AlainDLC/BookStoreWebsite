<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results for ${keyword} DLC E-LEARNING STORE</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="left">
		<c:if test="${fn:length(result)== 0 }">
			<center><h2>No Results for : ${keyword}</h2></center>
		</c:if>
	<c:if test="${fn:length(result) > 0 }">
		<div align="center" style="width: 80%; margin: 0 auto;">
			<h2>Results for : ${keyword}</h2>
			<c:forEach items="${result}" var="book">
			<div align="left">
				<div style=" display: inline-block; margin: 20px; width:10% " >
					<div>
						<a href="view_book?id=${book.bookId}"> <img
							src="data:image/jpg;base64,${book.base64Image}" width="128"
							height="164" />
						</a>
					</div>
				</div>
				<div style="display: inline-block; margin: 20px; vertical-align: top; width: 60%" align="left">
					<div>
						<h2><a href="view_book?id=${book.bookId}"> <b>${book.title}</b></a></h2>
					</div>
					<div>Rating *****</div>
					<div>
						<i>by ${book.author}</i>
					</div>
					<div>
						<p>${fn:substring(book.description,0,100)}...</p>
					</div>
				</div>
					<div style="display: inline-block; margin: 20px; vertical-align: top;">
						<h3>$ ${book.price}</h3>
						<h3><a href="">Add To Cart</a></h3>
				</div>
				</div>
				</c:forEach>

			</div>
		</c:if>
	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
</html>
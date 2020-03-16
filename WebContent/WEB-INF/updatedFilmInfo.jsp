<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
		<c:when test="${! empty film}">
		<h2>Title: ${film.title }</h2>
			<ul>
				<li>Film ID: ${film.id }</li>
				<li>Rating: ${film.rating }</li>
				<li>Release Year: ${film.year }</li>
				<li>Language: ${film.languageID }</li>
				<li>Rental Duration: ${film.rentalDuration }</li>
				<li>Rental Rate: ${film.rentalRate}</li>
				<li>Replacement Cost: ${film.replacementCost }</li>
				<li>Rating: ${film.rating }</li>
				<li>Special Features: ${film.specialFeatures }</li>
				<li>Length: ${film.length }</li>
				<br>
				<li><strong>Description: </strong>${film.description }</li>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>
	<a href="index.html">Take me hoommmeee country roadsss</a>
</body>
</html>
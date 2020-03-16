<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Films By Keywords</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty films}">
			<h2>All films found:</h2>
			<table>
				<c:forEach items="${films }" var="film">
					<tr>
					<td>${film.title }</td>
					<td>${film.rating }</td>
					<td>${film.year }</td>
					<td>${film.rentalRate }</td>
					<td>${film.rentalDuration }</td>
					</tr>
					<tr><td>${film.description }</td></tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>

</body>
</html>
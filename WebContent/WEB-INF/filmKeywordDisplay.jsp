<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
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
					<tr>
						<td>${film.description }</td>
					</tr>
					<tr>
					<td>
					<c:choose>
						<c:when test="${! empty film.actors }">
							<ul>
								<c:forEach items="${film.actors }" var="actor">
									<li>Actor Name: ${ actor.firstName} ${ actor.lastName}</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<p>No actors found</p>
						</c:otherwise>
					</c:choose>
					</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>

</body>
</html>
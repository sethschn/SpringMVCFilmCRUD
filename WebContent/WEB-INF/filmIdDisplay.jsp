<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FILM</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty film}">
			<ul>
				<li>Name: ${film.title}</li>
				<li>ID: ${film.id}</li>
				<li>Description: ${film.description}</li>
				<li>Rating: ${film.rating}</li>
			</ul>
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
		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>
	<form:form action="DeleteFilm.do" method="POST">
		<input type="hidden" name="filmid" value="${film.id}" />
		<input type="submit" value="Delete" />
	</form:form>

	<form:form action="UpdateFilmData.do" method="POST">
	  Title: <input type="text" name="title" value="${film.title }">
		<br>
	  Year: <input type="number" name="year" value="${film.year }">
		<br>
	  Description: <input type="text" name="description"
			value="${film.description }">
		<br>
		<input type="text" name="rating" value="${film.rating }">
		<br>
		<input type="number" name="length" value="${film.length }">
		<br>
		<input type="text" name="specialFeatures"
			value="${film.specialFeatures }">
		<br>
		<input type="text" name="language" value="${film.language }">
		<br>
		<input type="hidden" name="id" value="${film.id}" />
		<input type="hidden" name="languageID" value="${film.languageID}" />
		<input type="submit" value="Update" />
	</form:form>
</body>
</html>
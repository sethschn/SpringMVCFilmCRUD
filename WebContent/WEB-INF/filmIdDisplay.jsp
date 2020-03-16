<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
    </c:when>
    <c:otherwise>
      <p>No film found</p>
    </c:otherwise>
  </c:choose>
  <form:form action="DeleteFilm.do" method="POST">
  <input type="hidden" name="filmid" value="${film.id}"/>
  <input type="submit" value="Delete" />
  </form:form>
  
  <form:form action="UpdateFilmData.do" method="POST">
  <input type="text" name="title" value="${film.title }">
  <input type="number" name="year" value="${film.year }">
  <input type="text" name="description" value="${film.description }">
  <input type="text" name="rating" value="${film.rating }">
  <input type="number" name="length" value="${film.length }">
  <input type="text" name="specialFeatures" value="${film.specialFeatures }">
  <input type="text" name="language" value="${film.language }">
  <input type="hidden" name="id" value="${film.id}"/>
  <input type="hidden" name="languageID" value="${film.languageID}"/>
  <input type="submit" value="Update" />
  </form:form>
</body>
</html>
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
</body>
</html>
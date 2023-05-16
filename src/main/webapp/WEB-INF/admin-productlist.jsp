<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<!-- Vi skriver ../ foran error fordi vi er i WEB-INF mappen, og error ligger kun i webapp mappen, så vi går én mappe tilbage ved at skrive ../ -->

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

  <jsp:attribute name="footer">
            Admin Produktliste
    </jsp:attribute>


  <jsp:body>

    <div class="container">
      <h1>Admin Users</h1>
      <div class="row justify-content-end">
        <div class="col-md-6">
          <form>
            <div class="text-end mt-4">
              <input type="button" value="Tilbage" onclick="history.back()">
            </div>
          </form>
        </div>
      </div>
    </div>


    <table class="table table-striped mt-4">
      <thead>
      <tr>
        <th class="text-start">Id</th>
        <th class="text-center">Navn</th>
        <th class="text-center">Beskrivelse</th>
        <th class="text-center">Unit</th>
        <th class="text-center">Pris pr. enhed</th>
        <th class="text-end">Type</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="user" items="${requestScope.userList}">
        <tr>
          <td class="text-start align-middle"> ${user.firstname}</td>
          <td class="text-center align-middle">${user.lastname}</td>
          <td class="text-center align-middle">${user.email}</td>
          <td class="text-end align-middle">+45 ${user.phonenumber}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>


  </jsp:body>

</t:pagetemplate>
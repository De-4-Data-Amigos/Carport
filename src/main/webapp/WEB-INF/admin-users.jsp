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
            Admin Users
    </jsp:attribute>


    <jsp:body>

        <div class="container">
            <h1>Admin Users</h1>
            <div class="row justify-content-end">
                <div class="col-md-6">
                    <form>
                        <div class="text-end">
                            <input type="button" value="Tilbage" onclick="history.back()">
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <table class="table table-striped mt-4">
            <!--   <th>
               <td class="text-start">Name</td>
               <td class="text-center">Date created</td>
               <td class="text-end">Actions</td>
               <th/>
            -->
            <c:forEach var="user" items="${requestScope.userList}">
                    <tr>
                        <td class="text-start align-middle"> ${user.firstname}</td>
                        <td class="text-center align-middle">${user.lastname}</td>

                    </tr>
            </c:forEach>
        </table>

    </jsp:body>

</t:pagetemplate>
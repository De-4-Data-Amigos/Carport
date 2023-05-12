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
            Admin Hub
    </jsp:attribute>


    <jsp:body>

        <h1>Admin Hub</h1>
        <div>
            <a href="${pageContext.request.contextPath}/admin-users"><b>Admin users</b></a><br/>
            <a href="${pageContext.request.contextPath}/admin-orders"><b>Admin orders</b></a>
        </div>
    </jsp:body>

</t:pagetemplate>
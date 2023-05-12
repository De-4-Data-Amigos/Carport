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

        <div class="container">
            <h1 class="text-center">Admin Hub</h1>
            <div class="row justify-content-center">
                <div class="col-md-auto">
                    <a href="${pageContext.request.contextPath}/admin-users" class="h5"><b>Admin users</b></a><br/>
                </div>
                <div class="col-md-auto">
                    <a href="${pageContext.request.contextPath}/admin-orders" class="h5"><b>Admin orders</b></a><br/>
                </div>
                <div class="col-md-auto">
                    <a href="${pageContext.request.contextPath}/itemlist" class="h5"><b>Item list</b></a><br/>
                </div>
            </div>
        </div>


    </jsp:body>

</t:pagetemplate>
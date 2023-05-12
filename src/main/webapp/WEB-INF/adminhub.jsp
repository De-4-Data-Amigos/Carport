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
            <p>
                På Admin Hub kan du håndtere brugerkonti og adgangsrettigheder ved at klikke på "Admin users". Dette giver dig mulighed for at oprette, redigere eller slette brugerkonti og administrere, hvilke dele af systemet de har adgang til.<br>

                Hvis du vil administrere ordrer og salg, kan du klikke på "Admin orders". Her kan du se en oversigt over eksisterende ordrer, ændre status for ordrer og håndtere eventuelle problemer eller spørgsmål fra kunder.<br>

                Hvis du har brug for at opdatere eller tilføje nye produkter til systemet, kan du klikke på "Item list". Dette giver dig mulighed for at se en liste over tilgængelige produkter og redigere deres egenskaber, såsom pris eller beskrivelse.<br>

                Admin Hub gør det nemt for dig at udføre administrative opgaver uden at skulle have tekniske færdigheder eller kendskab til programmering. Klik blot på de relevante links for at få adgang til de ønskede funktioner og forenkle din daglige arbejdsgang.<br>
            </p>
        </div>


    </jsp:body>

</t:pagetemplate>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="true" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Hov!
    </jsp:attribute>

    <jsp:attribute name="footer">
            Error page
    </jsp:attribute>

    <jsp:body>
        <br>
        <p>... det ligner at du har skrevet forkerte login-detaljer da du forsøgte at logge ind.</p>

        <c:if test="${pageContext.errorData.statusCode == 404 }">
            p> Tryk venligst  <a href="login.jsp">her</a> for at komme tilbage til login-siden<br> eller benyt dette <a href="register.jsp">link</a> hvis du endnu ikke er oprettet som kunde.</p>
        </c:if>

        <c:if test="${pageContext.errorData.statusCode == 500 }">
            p> Tryk venligst  <a href="login.jsp">her</a> for at komme tilbage til login-siden<br> eller benyt dette <a href="register.jsp">link</a> hvis du endnu ikke er oprettet som kunde.</p>
        </c:if>


        <c:if test="${requestScope.errormessage != null}">
            <p> Tryk venligst  <a href="login.jsp">her</a> for at komme tilbage til login-siden<br> eller benyt dette <a href="register.jsp">link</a> hvis du endnu ikke er oprettet som kunde.</p></p>
        </c:if>

        <c:if test="${requestScope.errormessage  == null}">
            <p> Tryk venligst  <a href="login.jsp">her</a> for at komme tilbage til login-siden<br> eller benyt dette <a href="register.jsp">link</a> hvis du endnu ikke er oprettet som kunde.</p>
        </c:if>

        <br>
        <br>
        <br>
        <br>

        <div class="col-md-4">
            <div class="h-48 p-5 bg-light border rounded-3">
                <p><em>Brug for øvrig hjælp? Vi står klar her:</em></p>
                <div class="col">
                    <p><img src="images/mail.png" width="35px;" class="img-fluid align-left"/><em>   Kontakt@Fog.dk</em></p>
                    <p><img src="images/tlf.png" width="35px;" class="img-fluid align-left"/><em>  +45 40404040</em></p>
                </div>
            </div>
        </div>

        <br>

    </jsp:body>
</t:pagetemplate>
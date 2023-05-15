<%--
  Created by IntelliJ IDEA.
  User: rasmu
  Date: 11-05-2023
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>


<t:pagetemplate>
    <jsp:attribute name="header">
         Checkout
    </jsp:attribute>

    <jsp:body>

        <div class="container py-4">

            <div class="row align-items-md-stretch">
                <div class="col-md-8">
                    <div class="h-100 p-5 bg-light border rounded-3">

                        <h2>Bekræft venligst indholdet i din kurv</h2>
                        <form method="post">
                            <table class="table table-striped mt-4">
                                <thead>

                                <tr>
                                    <td class="text-start align-middle" style="color: #6f42c1"><h5>Type</h5>
                                    </td>
                                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Mål</h5>
                                    </td>
                                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Pris*</h5>
                                    </td>
                                </tr>
                                </thead>


                                    <tr>
                                        <td class="text-start align-middle"><img src="images/Carport.png" width="120px;" class="img-fluid align-left"/> Carport, fladt tag </td>
                                        <td class="text-center align-middle">${requestScope.width}cm x ${requestScope.length}cm</td>
                                        <td class="text-center align-middle">${requestScope.getPrice}</td>
                                        <td class="text-end"></td>
                                    </tr>


                                <%-- <c:forEach var="cupcake" items="${sessionScope.confirm_order.cupcakes}" varStatus="loop">
                                    <tr>
                                        <td class="text-start align-middle"> Cupcake #${loop.count} </td>
                                        <td class="text-center align-middle">${cupcake.cupcakeTopFlavor}</td>
                                        <td class="text-center align-middle">${cupcake.cupcakeBottomFlavor}</td>
                                        <td class="text-end">
                                        </td>
                                    </tr>
                                </c:forEach> --%>

                            </table>
                        </form>

                        <p><em>En ordrebekræftelse sendes til din email.</em></p>
                        <p><em>*Bemærk at prisen kan variere 5-10% afhængigt af endelig størrelse.</em></p>

                    </div>

                </div>
                <div class="col-md-4">
                    <div class="h-48 p-5 bg-light border rounded-3">
                        <h5>Har du brug for hjælp?</h5><br>
                        <div class="col">
                            <p><img src="images/mail.png" width="35px;" class="img-fluid align-left"/><em>   Kontakt@Fog.dk</em></p>
                           <p><img src="images/tlf.png" width="35px;" class="img-fluid align-left"/><em>  +45 40404040</em></p>
                            <p><em>Bemærk at der <b>altid</b> er gratis fragt hos Fog!</em></p>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-info margin-top">Bekræft bestilling</button>


                    <%-- <div class="h-48 p-5 bg-light border rounded-3 mt-2">
                        <div class="h-48 p-5 bg-light border rounded-3 mt-2">
                            <h5>Vidste du.. </h5>
                            <h7>.. at Olsker Cupcakes er et lokalt ejet og bæredygtigt bageri?</h7><br>
                            <h7>.. at vi er et sjette generations bageri med rødder tilbage til forrige århundrede?</h7><br>
                            <h7>.. at vi udelukkende bruger økologiske råvarer til vores cupcakes?</h7>
                        </div>
                    </div> --%>
                </div>
            </div>

            <footer class="pt-3 mt-4 text-muted border-top">
            </footer>
        </div>

    </jsp:body>

</t:pagetemplate>
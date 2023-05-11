<%--
  Created by IntelliJ IDEA.
  User: rasmu
  Date: 09-05-2023
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
Ordrebekræftelse
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>


    <jsp:body>

        <div class="container py-4">

        <div class="row align-items-md-stretch">
            <div class="col-md-6">
                <div class="h-100 p-5 bg-light border rounded-3">

                    <h2>Din kurv(%t):</h2>
                    <form method="post">

                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#Ordre nr.</th>
                                <th scope="col">Type</th>
                                <th scope="col">Mål</th>
                                <th scope="col">Pris</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>test</td>
                                <td>test</td>
                                <td>test</td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>test</td>
                                <td>test</td>
                                <td>test</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>Victor</td>
                                <td>Kussen</td>
                                <td>Dunse</td>
                            </tr>
                            </tbody>
                        </table>
                        <!-- <table class="table table-striped mt-4">
                            <thead>
                            <tr>
                                <td class="text-start align-middle" style="color: #6f42c1"><h5>Type</h5>
                                </td>
                                <td class="text-center align-middle" style="color: #6f42c1"><h5>Mål</h5>
                                </td>
                                <td class="text-center align-middle" style="color: #6f42c1"><h5>Pris</h5>
                                </td>
                            </tr>
                            </thead> --!>



                </div>
            </div>
        </div>
        <div class="container py-4">
            <div class="col-md-3 ">
                <div class="h-48 p-5 bg-light border rounded-3 mt-2">
                    <ul class="list-group mb-3">
                        <h5>Har du brug for hjælp?</h5><br>
                        <div class="col text-end">
                            <img src="images/mail.png" width="30px;" class="img-fluid"/><p><em>Kontakt@Fog.dk</em></p>
                            <img src="images/tlf.png" width="30px;" class="img-fluid"/><p>+45 40404040</p>
                        </div>
                </div>
            </div>
        </div>

    </jsp:body>

</t:pagetemplate>

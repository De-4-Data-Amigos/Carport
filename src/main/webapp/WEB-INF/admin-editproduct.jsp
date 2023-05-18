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
            Admin Edit Product
    </jsp:attribute>


    <jsp:body>

        <div class="container">
            <h1>Admin Edit Product</h1>
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

        <form method="post">
                <input style="width: 300px" type="text" name="edit">
            



                ${product.name}${product.description}${product.unit}${product.pricePrUnit}${product.type}${product.id}${product.productId}${product.length}${product.width}


        </form>


    </jsp:body>

</t:pagetemplate>
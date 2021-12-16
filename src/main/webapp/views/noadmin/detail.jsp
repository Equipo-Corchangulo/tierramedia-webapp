<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8">
    <title>Itinerario</title>
    <jsp:include page="/partials/includes.jsp"></jsp:include>
    <script type="text/javascript">
        window.addEventListener('DOMContentLoaded', function() {
            $('.datatable').DataTable({
                "order": [
                    [0, "asc"]
                ]
            });
        })
    </script>
</head>
<body class="text-center">
<jsp:include page="/partials/nav.jsp"></jsp:include>


<div class="container">
        <div class=" centrado">

            <div class="card  shadow card text-center">
                <img class="card-img-top" src="/tierramedia/uploadfiles/${ facturable.esPromocion()?'promociones.png': facturable.getImageDir() }" alt="Card image cap">
                <div class="card-body">
                    <h4>${facturable.toString() }</h4>
                    <a  href="/tierramedia/buy.do?id=${ facturable.getID() }&promo=${facturable.esPromocion()}" class="btn btn-success col-md-10 ${!user.puedeComprar(facturable)? 'disabled' : '' }" >Comprar</a>
                </div>
            </div>
        </div>

        <br>
        <br>
    </div>

</body>

</html>
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
	
	
			
			<h1>
				Hola : ${user.nombre } Conocé tu itinerario
			</h1>
			<div class="row row-cols-1 row-cols-md-3">
					<c:set var = "itinerario" scope = "session" value = "${user.getItinerario()}"/>
	                <c:forEach items="${ itinerario.getListaDeVisitas() }" var="atraccion">
	                	<div class="col mb-4">
	                    <div class="card" style="width: 18rem;">
						 <img class="card-img-top" src="/tierramedia/uploadfiles/${ atraccion.esPromocion()?'promociones.png': atraccion.getImageDir() }" alt="Italian Trulli">
						 <div class="card-body">
						   <h5 class="card-title"><c:out value="${ atraccion.esPromocion()?atraccion.getNombreDePromocion():atraccion.getNombre() }"></c:out></h5>
						   <h5 class="card-text">Tiempo Promedio: <c:out value="${ itinerario.getHorasNecesarias() }"></c:out></h5>
							<h5 class="card-text">Costo Total: <c:out value="${ itinerario.getCostoMonedas() }"></c:out></h5>
						   <div class="card-footer">
						    <a href="#" class="btn btn-primary">Ver más atracciones</a>
						    </div>
						  </div>
						</div>	
						</div>	                   
					</c:forEach>
					</div>
				
</div>

</body>

</html>
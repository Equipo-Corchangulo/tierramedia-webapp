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



	<div class="bg-light p-4 rounded">
			<h1>
				Conocé tu itinerario
			</h1>
			
	                <c:forEach items="${ atraccionList }" var="atraccion">
						
		                        <c:choose>
							           <c:when test="${ PerfilUsuario.comprar() }">
		                            <div class="card" style="width: 18rem;">
									  <img class="col-md-6" src="/tierramedia/uploadfiles/${ Atraccion.getImageDir() }" alt="Italian Trulli">
									  <div class="card-body">
									    <h5 class="card-title"><c:out value="${ atraccion.getNombre() }"></c:out></h5>
									    <h5 class="card-title"><c:out value="${ itinerario.getHorasNecesarias() }"></c:out></h5>
									 	<h5 class="card-title"><c:out value="${ itinerario.getCostoMonedas() }"></c:out></h5>
									    <h5 class="card-title"><c:out value="${ itinerario.getListaDeVisitas() }"></c:out></h5>
									   
									    <a href="#" class="btn btn-primary">Ver más atracciones</a>
									  </div>
									</div>
		                            
		                            </c:when>
							
						</c:choose>
		                   
					</c:forEach>
				
</div>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Lista Usuarios</title>
    <jsp:include page="/partials/includes.jsp"></jsp:include>
    <script type="text/javascript">
        window.addEventListener('DOMContentLoaded', function() {
            $('select').selectpicker();
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

    <div class="row pt-3 d-inline-flex justify-content-center">
        <a type="button" href="modelchange.adm" class="btn btn-success d-inline-flex justify-content-center">
            <i class="material-icons">add</i> Nueva promocion</a>
    </div>

    <div class="row">
        <div class="col-md-9 mx-auto pt-3">
            <table class="table datatable">
                <thead>
                     <tr class="table-light">
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Tipo de promocion</th>
                        <th scope="col">Tipo de atracciones</th>
                        <th scope="col">Tiempo Promedio</th>
                        <th scope="col">Costo toal</th>
                        <th scope="col">Atracciones Asociadas</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
	                <c:forEach items="${ promocionList }" var="promocion">
						<c:if test="${ promocion.isActive() }">
							<tr>
		                        <th scope="row"><c:out value="${ promocion.getID() }"></c:out></th>
		                        <td><c:out value="${ promocion.getNombreDePromocion() }"></c:out></td>
		                        <td><c:out value="${ promocion.getTipoPromo().toString()}"></c:out></td>
		                        <td><c:out value="${ promocion.getTipoDeAtraccion().getName() }"></c:out></td>
		                        <td><c:out value="${ promocion.obtenerTiempoTotal() }"></c:out></td>
		                        <td><c:out value="${ promocion.obtenerCostoTotal() }"></c:out></td>
		                        <td>
		                        	<c:forEach items="${ promocion.getListaDeAtracciones() }" var="atraccion">
		                        		| <c:out value='${ atraccion.getNombre()}'></c:out> |
		                        	</c:forEach>
		                        </td>
		                        <td>
		                            <div class="row justify-content-between">
		                            	<c:if test="${ user.isAdmin() }">
			                                <a class="btn btn-primary col-sm-5 d-inline-flex justify-content-center" href="modelchange.adm?id=${ promocion.getID() }">
			                                    <i class="material-icons">edit</i></a>
			                                <a class="btn btn-danger col-sm-5 d-inline-flex justify-content-center" href="delete.adm?id=${ promocion.getID() }">
			                                    <i class="material-icons">delete</i></a>
			                            </c:if>
			                            <c:if test="${!user.isAdmin() }">
			                            	<a class="btn btn-primary col-sm-5 d-inline-flex justify-content-center" href="/tierramedia/buy.do?id=${ promocion.getID() }&promo=${true}">
												<i class="material-icons">shopping_cart</i></a>
			                            </c:if>
		                            </div>
		                        </td>
		                    </tr>
						</c:if>
					</c:forEach>
				</tbody>
            </table>
        </div>

    </div>
</body>

</html>
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
        <a type="button" href="create.adm" class="btn btn-success d-inline-flex justify-content-center">
            <i class="material-icons">add</i> Nueva Atracción</a>
    </div>

    <div class="row">
        <div class="col-md-9 mx-auto pt-3">
            <table class="table datatable">
                <thead>
                    <tr class="table-light">
                         <th scope="col">Id</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Costo</th>
                            <th scope="col">Tiempo promedio</th>
                            <th scope="col">Tipo de Atraccion</th>
                            <th scope="col">Cupo</th>
                            <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
	                <c:forEach items="${ atraccionList }" var="atraccion">
						<c:if test="${ atraccion.isActive() }">
							<tr>
		                        <th scope="row"><c:out value="${ atraccion.getID() }"></c:out></th>
		                        <td><c:out value="${ atraccion.getNombre() }"></c:out></td>
		                        <td><c:out value="${ atraccion.obtenerCostoTotal() }"></c:out></td>
		                        <td><c:out value="${ atraccion.obtenerTiempoTotal() }"></c:out></td>
		                        <td><c:out value="${ atraccion.getTipoAtraccion().toString() }"></c:out></td>
		                        <td><c:out value="${ atraccion.getCupo() }"></c:out></td>
		                        <td>
		                            <div class="row justify-content-between">
		                                <a class="btn btn-primary col-sm-5 d-inline-flex justify-content-center" href="edit.adm?id=${ atraccion.getID() }">
		                                    <i class="material-icons">edit</i></a>
		                                <a class="btn btn-danger col-sm-5 d-inline-flex justify-content-center" href="delete.adm?id=${ atraccion.getID() }">
		                                    <i class="material-icons">delete</i></a>
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
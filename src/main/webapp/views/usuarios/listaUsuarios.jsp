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
            <i class="material-icons">add</i> Nuevo Usuario</a>
    </div>

    <div class="row">
        <div class="col-md-9 mx-auto pt-3">
            <table class="table datatable">
                <thead>
                    <tr class="table-light">
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Cantidad de Monedas</th>
                        <th scope="col">Tiempo disponible</th>
                        <th scope="col">Atracción Preferida</th>
                        <th scope="col">Tipo de User</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
	                <c:forEach items="${ userList }" var="usuario">
						<c:if test="${ usuario.isActive() }">
							<tr>
		                        <th scope="row"><c:out value="${ usuario.getId() }"></c:out></th>
		                        <td><c:out value="${ usuario.getNombre() }"></c:out></td>
		                        <td><c:out value="${ usuario.getPresupuesto() }"></c:out></td>
		                        <td><c:out value="${ usuario.getTiempoDisponible() }"></c:out></td>
		                        <td><c:out value="${ usuario.getTipoDeAtraccion().getName() }"></c:out></td>
		                        <td><c:out value='${ usuario.isAdmin()?" Admin": "Regular" }'></c:out></td>
		                        <td>
		                            <div class="row justify-content-between">
		                                <a class="btn btn-primary col-sm-5 d-inline-flex justify-content-center" href="edit.adm?id=${ usuario.getId() }">
		                                    <i class="material-icons">edit</i></a>
		                                <a class="btn btn-danger col-sm-5 d-inline-flex justify-content-center" href="delete.adm?id=${ usuario.getId() }">
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
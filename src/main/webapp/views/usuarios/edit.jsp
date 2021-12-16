<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Nuevo Usuario</title>
   <jsp:include page="/partials/includes.jsp"></jsp:include>
</head>

<body class="text-center">
	<jsp:include page="/partials/nav.jsp"></jsp:include>
    
    <div class="row">
        <div class="col-sm-4 centrado">
            <div class="card border-success">
                <div class="card-header text-white bg-success">
                    <h4 class="card-title">Usuario</h4>
                </div>
                <div class="card-body">
                    <form action="edit.adm?id=${ userToEdit.getId()}" method="post">
                        <div class="form-floating pt-2">
                            <input name="nombre" type="text" class="form-control" id="floatingInput" placeholder="name@example.com" value="${userToEdit.getNombre() }">
                            <label for="floatingInput">Nombre</label>
                        </div>
                        <div class="form-floating pt-2">
                            <input  name="username" type="email" class="form-control" id="floatingInput" value="${userToEdit.getUsername() }"placeholder="name@example.com">
                            <label for="floatingInput">Email</label>
                        </div>
                        <div class="form-floating pt-2">
                            <input  name="password" type="password" class="form-control" id="floatingPassword" placeholder="Password" value="${userToEdit.getPassword() }">
                            <label for="floatingPassword">Contraseña</label>
                        </div>
                        <div class="form-floating pt-2">
                            <input  name="money" type="number" class="form-control" id="floatingInput" value="${userToEdit.getPresupuesto() }" placeholder="name@example.com">
                            <label for="floatingInput">Presupuesto Incial</label>
                        </div>
                        <div class="form-floating pt-2">
                            <input name="tiempo" type="number" class="form-control" id="floatingInput" placeholder="name@example.com" value="${userToEdit.getTiempoDisponible() }">
                            <label for="floatingInput">Tiempo Disponible</label>
                        </div>
                        <div class="form-floating pt-2">
                            <input class="form-control" name="tipo" list="tipo" id="exampleDataList" placeholder="Type to search..." value="${userToEdit.getTipoDeAtraccion().getName() }">
                            <label for="floatingInput">Atraccion De Preferencia</label>
                            <datalist id="tipo">
				                <option value="Aventura">
				                <option value="Degustacion">
				                <option value="Paisaje">
			                </datalist>
                        </div>
                        <div class="form-floating pt-2">
                            <input class="form-control" name="usertype" list="admin" id="exampleDataList" placeholder="Type to search..." value ="${userToEdit.isAdmin()?'Admin': 'Regular' }">
                            <label for="floatingInput">Tipo de usuario</label>
                            <datalist id="admin">
				                <option value="Admin">
				                <option value="Regular">
			                </datalist>
                        </div>
                        <div class="row pt-3 justify-content-around">
                            <button class="col-md-4 btn btn-success" type="submit"><c:if test="${viewState.equals('create')}">Crear</c:if> <c:if  test="${!viewState.equals('create')}">editar</c:if></button>
                            <a class="col-md-4 btn btn-danger align-self-center" type="button" href="javascript:history.back()">Cancelar</a>
                        </div>

                    </form>
                    <c:forEach items="${errors}" var="error">
				<div class="alert alert-danger alert-dismissible fade show d-flex align-items-center" role="alert">
				  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
				  <strong>Error! en campo: ${error.key}  ${error.value}</strong>
			  	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			</c:forEach>
                </div>
            </div>

        </div>
    </div>
</body>

</html>
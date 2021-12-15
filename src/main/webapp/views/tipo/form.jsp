<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Nuevo Tipo de Atraccion</title>
   <jsp:include page="/partials/includes.jsp"></jsp:include>
</head>

<body class="text-center">
	<jsp:include page="/partials/nav.jsp"></jsp:include>

   <div class="row">
           <div class="col-sm-4 centrado">
               <div class="card  border-success">
                   <div class="card-header text-white bg-success">
                       <h4 class="card-title">Nuevo tipo de atracción</h4>
                   </div>
                   <div class="card-body">
                       <form
                           action="modelchange.adm${viewState != 'create' ? '?id=' : ''}${viewState != 'create' && tipoAtraccion != null ? String.valueOf(tipoAtraccion.getID()) :''}"
                            method="post"
                       >
                           <div class="form-floating">
                               <input name="nombre" type="text" class="form-control" id="nombre" placeholder="name@example.com" <c:if test = "${tipoAtraccion != null}">value = "${tipoAtraccion.getName()}" </c:if> >
                               <label for="nombre">Nombre</label>
                           </div>

                           <div class="row pt-3 justify-content-around">
                               <button class="col-md-4 btn btn-success" type="submit"><c:if test="${viewState.equals('create')}">Crear</c:if> <c:if  test="${!viewState.equals('create')}">editar</c:if></button>
                               <a class="col-md-4 btn btn-danger align-self-center" type="button" href="javascript:history.back()">Cancelar</a>
                           </div>
                       </form>
                   </div>
               </div>
			<c:forEach items="${errors}" var="error">
			<div class="alert alert-danger alert-dismissible fade show d-flex align-items-center" role="alert">
			  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
			  <strong>Error! en campo: ${error.key}  ${error.value}</strong>
		  	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			</c:forEach>
           </div>
       </div>
</body>

</html>
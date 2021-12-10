<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Nueva promoción</title>
   <jsp:include page="/partials/includes.jsp"></jsp:include>
</head>

<body class="text-center">
	<jsp:include page="/partials/nav.jsp"></jsp:include>

   <div class="row">
           <div class="col-sm-4 centrado">
               <div class="card  border-success">
                   <div class="card-header text-white bg-success">
                       <h4 class="card-title">Nueva promoción</h4>
                   </div>
                   <div class="card-body">
                       <form
                           action="modelchange.adm${promocion != null ? '?id=' : ''}${promocion != null ? String.valueOf(promocion.getID()) :''}"
                            method="post"
                       >
                          <div class="form-floating pt-2">
                              <input name="nombre" type="text" class="form-control" id="nombre" placeholder="name@example.com" <c:if test = "${promocion != null}">value = "${promocion.getNombreDePromocion()}"</c:if> >
                              <label for="nombre">Nombre de promoción</label>
                          </div>
                          <div class="form-floating pt-2">
                              <input name="descripcion" type="text" class="form-control" id="descripcion" placeholder="name@example.com" <c:if test = "${promocion != null}">value = "${promocion.getDescripcion()}"</c:if> >
                              <label for="descripcion">Descripción</label>
                          </div>
                          <div class="form-floating pt-2">
                              <select name="lista" class="form-select" id="lista" aria-label="Open this select menu">
                                  <option value="1">One</option>
                                  <option value="2">Two</option>
                                  <option value="3">Three</option>
                                </select>
                              <label for="lista">Lista de atracciones</label>
                          </div>

                          <div class="form-floating pt-2">
                               <input class="form-control" list="datalistOptions" id="tipo" <c:if test = "${promocion != null}">value = "${promocion.getTipoDeAtraccion().getName()}"</c:if>  placeholder="Type to search...">
                               <label for="tipo">Tipo de atracción</label>
                               <datalist id="datalistOptions">
                               <c:forEach items="${tipoAtraccionList}" var="tipoAtraccion">
                                   <option value="${tipoAtraccion.getName()}">
                               </c:forEach>
                               </datalist>
                          </div>
                          <div class="row pt-3 justify-content-around">
                              <button class="col-md-4 btn btn-success " type="submit"><c:if test="${viewState.equals('create')}">Crear</c:if> <c:if  test="${!viewState.equals('create')}">editar</c:if> promoción</button>
                              <a class="col-md-4 btn btn-danger align-self-center" role="button" href="javascript:history.back()">Cancelar</a>
                          </div>
                       </form>
                   </div>
               </div>

           </div>
       </div>
</body>

</html>
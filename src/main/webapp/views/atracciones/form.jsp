<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Nueva atracción</title>
   <jsp:include page="/partials/includes.jsp"></jsp:include>
</head>

<body class="text-center">
	<jsp:include page="/partials/nav.jsp"></jsp:include>

   <div class="row">
           <div class="col-sm-4 centrado">
               <div class="card  border-success">
                   <div class="card-header text-white bg-success">
                       <h4 class="card-title">Nueva atracción</h4>
                   </div>
                   <div class="card-body">
                       <form
                           action="modelchange.adm${atraccion != null ? '?id=' : ''}${atraccion != null ? String.valueOf(atraccion.getID()) :''}"
                           enctype="multipart/form-data"
                            method="post"
                       >
                        <div class="form-floating pt-2">
                            <input <c:if test = "${atraccion != null}">value = "${atraccion.getNombre()}"</c:if> name="nombre" type="text" class="form-control" id="nombre" placeholder="name@example.com">
                            <label for="nombre">Nombre de atracción</label>
                        </div>
                        <div class="form-floating pt-2">
                            <input <c:if test = "${atraccion != null}">value = "${atraccion.obtenerCostoTotal()}"</c:if> name="costo" type="number" class="form-control" id="costo" placeholder="name@example.com">
                            <label for="costo">Costo</label>
                        </div>
                        <div class="form-floating pt-2">
                            <input <c:if test = "${atraccion != null}">value = "${atraccion.obtenerTiempoTotal()}"</c:if> name="tiempo" type="number" class="form-control" id="tiempo" placeholder="name@example.com">
                            <label for="tiempo">Tiempo promedio</label>
                        </div>

                        <div class="form-floating pt-2">
                            <input <c:if test = "${atraccion != null}">value = "${atraccion.getCupoDiario()}"</c:if> name="cupo" type="number" class="form-control" id="cupo" placeholder="10.0">
                            <label for="cupo">Cupo Diario</label>
                        </div>
                        <div class="form-floating pt-2">
                            <input <c:if test = "${atraccion != null}">value = "${atraccion.getTipoAtraccion().getName()}"</c:if> name="tipo" class="form-control" list="datalistOptions" id="tipo" placeholder="Type to search...">
                            <label for="tipo">Tipo de atracción</label>
                            <datalist id="datalistOptions">
                            <c:forEach items="${tipoAtraccionList}" var="tipoAtraccion">
                                <option value="${tipoAtraccion.getName()}">
                            </c:forEach>
                            </datalist>
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlFile1">Imagen De atraccion</label>
                            <input type="file" name="file2" class="form-control-file" id="exampleFormControlFile1">
                          </div>
                        <div class="row pt-3 justify-content-around">
                            <button class="col-md-4 btn btn-success" type="submit">
                                <c:if test="${viewState.equals('create')}">Crear</c:if> <c:if  test="${!viewState.equals('create')}">editar</c:if> atracción</button>
                            <a class="col-md-4 btn btn-danger align-self-center" role="button" href="javascript:history.back()">Cancelar</a>
                        </div>
                       </form>
                   </div>
               </div>

           </div>
       </div>
</body>

</html>
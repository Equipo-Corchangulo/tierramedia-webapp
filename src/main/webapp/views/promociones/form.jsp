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
                           action="modelchange.adm${!viewState.equals('create')? '?id=' : ''}${!viewState.equals('create') && promocion != null ? String.valueOf(promocion.getID()) :''}"
                            method="post"
                       >
                          <div class="form-floating pt-2">
                                 <input name="tipoPromo"  class="form-control" list="tipoPromoOptions" id="tipoPromo" <c:if test = "${promocion != null}">value = "${promocion.getTipoPromo()}"</c:if>  placeholder="Type to search...">
                                 <label for="tipoPromo">Tipo de Promoción</label>
                                 <datalist id="tipoPromoOptions">
                                      <option value="AXB">
                                      <option value="PORCENTUAL">
                                      <option value="ABSOLUTA">
                                 </datalist>
                          </div>
                          <div class="form-floating pt-2">
                              <input name="nombre" type="text" class="form-control" id="nombre" placeholder="name@example.com" <c:if test = "${promocion != null}">value = "${promocion.getNombreDePromocion()}"</c:if> >
                              <label for="nombre">Nombre de promoción</label>
                          </div>
                          <div class="form-floating pt-2">
                              <input name="descripcion" type="text" class="form-control" id="descripcion" placeholder="name@example.com" <c:if test = "${promocion != null}">value = "${promocion.getDescripcion()}"</c:if> >
                              <label for="descripcion">Descripción</label>
                          </div>
                          <div class="form-floating pt-2">
                          <input name="atraccioneslist" id="hiddenlist" type="TEXT" hidden placeholder="Escribe nombres de atracciones">
                          <select id="lista" name="atracciones" multiple  data-show-all-suggestions class="form-control form-select"  aria-label="Disabled select example">
                             <c:forEach items="${atraccionList}" var="atraccion">
                                <option value="${atraccion.getID()}" <c:if test="${promocion.seEncuentraEnElFacturable(atraccion)}"> selected </c:if>> ${atraccion.getNombre()} </option>
                            </c:forEach>
                          </select>
                          <div class="invalid-feedback">Porfavor Selecciona una atraccion valida</div>
                              <label for="lista">Lista de atracciones</label>
                          </div>

                          <div class="form-floating pt-2">
                               <input name="tipo"  class="form-control" list="datalistOptions" id="tipo" <c:if test = "${promocion != null}">value = "${promocion.getTipoDeAtraccion().getName()}"</c:if>  placeholder="Type to search...">
                               <label for="tipo">Tipo de atracción</label>
                               <datalist id="datalistOptions">
                               <c:forEach items="${tipoAtraccionList}" var="tipoAtraccion">
                                   <option value="${tipoAtraccion.getName()}">
                               </c:forEach>
                               </datalist>
                          </div>
                          <div class="form-floating pt-2" id="porcentaje">
                            <input name="porcentaje" type="text" class="form-control" id="porcentajein"<c:if test = "${promocion != null && promocion.getTipoPromo()== 'PORCENTUAL'}">value = "${promocion.getPorcentajeDescuento()}"</c:if> >
                            <label for="porcentajein">Porcentaje de descuento</label>
                          </div>
                          <div class="form-floating pt-2" id="costoFinal">
                            <input name="costoFinal" type="text" class="form-control" id="nombre" placeholder="name@example.com" <c:if test = "${promocion != null && promocion.getTipoPromo()== 'ABSOLUTA'}">value = "${promocion.obtenerCostoTotal()}"</c:if> >
                            <label for="nombre">Costo Final</label>
                          </div>
                          <div class="form-floating pt-2" id="extra">
                            <select id="extrain" name="extra" class="form-control form-select" aria-label="Disabled select example" <c:if test = "${promocion != null && promocion.getTipoPromo()== 'AXB'}">value = "${promocion.getAtraccionExtra().getID()}"</c:if>>
                            <option value=""></option>
                               <c:forEach items="${atraccionList}" var="atraccion">
                                  <option value="${atraccion.getID()}" <c:if test ="${promocion != null && promocion.getTipoPromo()== 'AXB' && promocion.getAtraccionExtra().getID() == atraccion.getID()}"> selected </c:if> > ${atraccion.getNombre()} </option>
                              </c:forEach>
                            </select>

                            <label for="extrain">Atraccion Extra</label>
                          </div>

                          <div class="row pt-3 justify-content-around">
                              <button class="col-md-4 btn btn-success " type="submit"><c:if test="${viewState.equals('create')}">Crear</c:if> <c:if  test="${!viewState.equals('create')}">editar</c:if> promoción</button>
                              <a class="col-md-4 btn btn-danger align-self-center" role="button" href="javascript:history.back()">Cancelar</a>
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
       <jsp:include page="/partials/promocionscript.jsp"></jsp:include>
</body>

</html>
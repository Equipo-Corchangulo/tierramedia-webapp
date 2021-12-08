<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container">
		<a class="navbar-brand" href="/">Tierra Media</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav me-auto mb-2 mb-md-0">
				<li class="nav-item">
				    <a class="nav-link <c:if test="${ selectedMenu.equals('usuario') }"> active </c:if>" href="/tierramedia/usuario/lista.adm">Usuarios</a>
				</li>
				<li class="nav-item">
				    <a class="nav-link <c:if test="${ selectedMenu.equals('atracciones') }"> active </c:if>" href="/tierramedia/atracciones/lista.adm">Atracciones</a>
				</li>
				<li class="nav-item">
				    <a class="nav-link <c:if test="${ selectedMenu.equals('promociones') }"> active </c:if>" href="/tierramedia/promociones/lista.adm">Promociones</a>
				</li>
				
				<li class="nav-item">
				    <a class="nav-link <c:if test="${ selectedMenu.equals('tipo') }"> active </c:if>" href="/tierramedia/tipos/lista.adm">Tipos de Atraccion</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
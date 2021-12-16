<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>Home</title>
<jsp:include page="/partials/homeincludes.jsp"></jsp:include>
</head>
<body class="text-center">
	<jsp:include page="/partials/nav.jsp"></jsp:include>



	<div class="bg-light p-4 rounded">
		<br>
		<h1>
			¡Bienvenido/a,
			<c:out value="${user.nombre}" />
			!
		</h1>
		<h4>Te presentamos algunas promociones que pueden interesarte</h4>
		<br>
	</div>





	<div class="container">


		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>



			<div class="carousel-inner">
				<c:set var="first" scope="session" value="${true}" />
				<c:forEach items="${listaDeFacturables }" var="facturable">

					<c:if
						test="${ facturable.isActive() && user.puedeComprar(facturable)}">
						<div class="carousel-item ${first?'active':'' }"
							onclick="verPromo(${facturable.getID()}, ${facturable.esPromocion() })">
							<img class="d-block w-100"
								<c:choose>
								           <c:when test="${ facturable.esPromocion() }">
								           		src="/tierramedia/uploadfiles/promociones.png" alt="no image">
								           </c:when>
								           <c:otherwise>
								           		src="/tierramedia/uploadfiles/${ facturable.getImageDir()}" alt="no image">
								           </c:otherwise>
						             </c:choose>
								<div class="carousel-caption d-none d-md-block ">
									    <h5 class="text-white">${facturable.esPromocion()? facturable.getNombreDePromocion(): facturable.getNombre() }</h5>
									    <p>Costo: ${facturable.obtenerCostoTotal() } Tiempo: ${facturable.obtenerTiempoTotal() }</p>
									  </div>
						</div>
						<c:set var="first" scope="session" value="${false}" />
					</c:if>
				</c:forEach>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>




		<div class="row pt-md-5">

			<h2 class="text-black">¿Quiénes somos?</h2>
			<br>

			<div class="row">
				<div class="col-md-6 pt-5">
					<h5>
						Tierra Media Turismo es una empresa con más de 20 años de
						trayectoria ofreciendo turismo de calidad y responsable con el
						medio ambiente. Nos basamos en las preferencias de nuestros
						clientes a la hora de ofrecer las atracciones y ofrecemos las
						mejores promociones del mercado. <b>Si buscás calidad, Tierra
							Media Turismo es para vos.</b>
					</h5>
				</div>
				<div class="col-md-6">
					<img src="assets/img/lonelym.jpg" class="rounded img-fluid"
						alt="Presentacion">
				</div>
			</div>

			<div class="row pt-3 d-inline-flex justify-content-center">

				<a type="button" href="/tierramedia/atracciones/lista"
					class="btn btn-success d-inline-flex justify-content-center"> <i
					class="material-icons"></i> Conocer atracciones
				</a>
			</div>
		</div>
		<br> <br>
		<div class="bg-dark p-3 rounded  text-white m-5">


			<h5 class="text-white">Diseño y programación</h5>

			<p class="text-center fs-6">Somos Corchángulo, un equipo de
				estudiantes de programación de Argentina Programa 2. Luego de 6
				meses trabajando se llegó al proyecto final, con mucho esfuerzo y
				dificultad y con las ganas destinadas a esto. ¡Que lo disfruten!</p>

		</div>

	</div>

	<br>
	<br>


</body>

</html>

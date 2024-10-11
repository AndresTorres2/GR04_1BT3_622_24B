<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Detalles de la Reserva</title>
  <style>
    body {
      background: #100f0f;
      color: #dcdcdc;
      font-family: Arial, sans-serif;
    }
    .container {
      width: 80%;
      margin: auto;
      padding: 20px;
      background: #1c1c1c;
      border-radius: 10px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
    }
    h1, h2 {
      text-align: center;
    }
    ul {
      list-style-type: none;
      padding: 0;
    }
    li {
      margin: 5px 0;
    }
    a {
      display: block;
      text-align: center;
      text-decoration: none;
      padding: 10px;
      background-color: #48578e;
      color: white;
      border-radius: 5px;
      margin: 20px 0;
    }
    a:hover {
      background-color: #71a8df;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Detalles de la Reserva</h1>
  <h2>Día seleccionado: ${dia}</h2>
  <h2>Jornada: ${reserva.bus.jornada}</h2>
  <h2>Bus #${reserva.bus.codigo}</h2>
  <h3>Fecha de la reserva: ${reserva.fechaReserva}</h3>
  <h4>Horario: ${reserva.bus.horario}</h4>
  <h4>Ruta:</h4>
  <ul>
    <li><strong>Desde:</strong> ${reserva.bus.ruta.origen}</li>
    <li><strong>Hasta:</strong> ${reserva.bus.ruta.destino}</li>
  </ul>
  <p><strong>Recorrido: </strong>
    <c:forEach var="calle" items="${reserva.bus.ruta.calles}" varStatus="status">
      ${calle.nombreCalle}
      <c:if test="${!status.last}">, </c:if>
    </c:forEach>
  </p>

  <p><strong>Nombre del estudiante:</strong> ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}</p> <!-- Nombre y apellido del estudiante -->
  <p><strong>Correo del estudiante:</strong> ${reserva.estudiante.email}</p>

  <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=cancelarReserva&idReserva=${reserva.idReserva}&dia=${dia}"
     onclick="return confirm('¿Está seguro de que desea cancelar la reserva?');">
    Cancelar Reserva
  </a>
</div>
</body>
</html>

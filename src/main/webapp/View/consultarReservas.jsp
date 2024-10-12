<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Consultar Reservas</title>
  <style>
    body {
      background: #100f0f;
      color: #dcdcdc;
      font-family: Arial, sans-serif;
    }

    .container {
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    .tabs {
      display: flex;
      justify-content: center;
      margin-bottom: 20px;
    }

    .tab {
      padding: 15px 30px;
      margin: 0 5px;
      background-color: #1c1c1c;
      border: 1px solid #dcdcdc;
      color: #dcdcdc;
      cursor: pointer;
      text-align: center;
      text-decoration: none;
    }

    .tab:hover {
      background-color: #48578e;
    }

    h2 {
      margin-bottom: 20px;
    }

    .reserva {
      background-color: #1c1c1c;
      padding: 20px;
      border: 1px solid #dcdcdc;
      margin-bottom: 15px;
      width: 80%;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Consultar Reservas</h1>

  <div class="tabs">
    <a class="tab" href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=verReservasDia&dia=Lunes">Lunes</a>
    <a class="tab" href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=verReservasDia&dia=Martes">Martes</a>
    <a class="tab" href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=verReservasDia&dia=Miercoles">Miércoles</a>
    <a class="tab" href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=verReservasDia&dia=Jueves">Jueves</a>
    <a class="tab" href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=verReservasDia&dia=Viernes">Viernes</a>
  </div>

  <h2>Reservas para el día: ${dia}</h2>

  <c:forEach var="reserva" items="${reservas}">
    <div class="reserva">
      <p><strong>Bus #${reserva.bus.codigo}</strong></p>
      <p>Fecha de la reserva: ${reserva.fechaReserva}</p>
      <p>Horario: ${reserva.bus.horario} (${reserva.bus.jornada})</p>
      <p>Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino}</p>
      <p>Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}</p>
    </div>
  </c:forEach>

</div>
</body>
</html>
